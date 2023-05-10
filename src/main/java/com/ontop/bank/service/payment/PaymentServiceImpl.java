package com.ontop.bank.service.payment;

import com.ontop.bank.infrastructure.repository.payment.PaymentEntityRepository;
import com.ontop.bank.infrastructure.repository.payment.PaymentStateEntityRepository;
import com.ontop.bank.infrastructure.repository.payment.entity.PaymentEntity;
import com.ontop.bank.infrastructure.repository.payment.entity.PaymentStateEntity;
import com.ontop.bank.infrastructure.repository.payment.mapper.PaymentRepositoryMapper;
import com.ontop.bank.infrastructure.repository.recipient.RecipientBankAccountEntityRepository;
import com.ontop.bank.infrastructure.repository.recipient.mapper.RecipientBankAccountRepositoryMapper;
import com.ontop.bank.service.exception.GeneralErrorWalletException;
import com.ontop.bank.service.exception.InvalidBankAccountException;
import com.ontop.bank.service.exception.InvalidPaymentException;
import com.ontop.bank.service.fee.FeeService;
import com.ontop.bank.service.model.fee.TransactionFee;
import com.ontop.bank.service.model.payment.Payment;
import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.payment.PaymentState;
import com.ontop.bank.service.model.payment.PaymentStatus;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.model.wallet.WalletTransaction;
import com.ontop.bank.service.provider.PaymentProviderServiceImpl;
import com.ontop.bank.service.wallet.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final RecipientBankAccountEntityRepository recipientBankAccountRepository;

    private final PaymentEntityRepository paymentRepository;

    private final PaymentStateEntityRepository paymentStateEntityRepository;

    private final WalletService walletService;

    private final PaymentProviderServiceImpl paymentProviderClient;

    private final FeeService feeService;
    private final PaymentRepositoryMapper paymentRepositoryMapper = PaymentRepositoryMapper.INSTANCE;

    private final RecipientBankAccountRepositoryMapper recipientBankAccountRepositoryMapper
            = RecipientBankAccountRepositoryMapper.INSTANCE;

    public PaymentServiceImpl(RecipientBankAccountEntityRepository recipientBankAccountRepository,
                              PaymentEntityRepository paymentRepository, PaymentStateEntityRepository paymentStateEntityRepository,
                              WalletService walletService,
                              PaymentProviderServiceImpl paymentProviderClient, FeeService feeService) {
        this.recipientBankAccountRepository = recipientBankAccountRepository;
        this.paymentRepository = paymentRepository;
        this.paymentStateEntityRepository = paymentStateEntityRepository;
        this.walletService = walletService;
        this.paymentProviderClient = paymentProviderClient;
        this.feeService = feeService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Payment createPaymentTransaction(Long userId, Double amount) {
        checkValidPaymentUser(userId);
        checkValidPaymentAmount(amount);
        checkUserFounds(userId, amount);

        RecipientBankAccount userRecipientBankAccount = getUserBankAccount(userId);

        WalletTransaction walletTransaction = walletService.createWithdrawWalletTransaction(userId, amount);
        return createPayment(userRecipientBankAccount, walletTransaction, amount);
    }

    @Override
    public List<Payment> getAllPayments(Long userId, Double amount, LocalDateTime createdFrom, LocalDateTime createdTo,
                                        int pageSize, int offSet) {
        return paymentRepository.findPayments(amount, createdFrom, createdTo, userId, pageSize, offSet)
                .stream()
                .map(paymentRepositoryMapper::toPayment)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void processAllPendingPayments() {
        List<Payment> payments = paymentRepository.getAllPendingPayments()
                .stream()
                .map(paymentRepositoryMapper::toPayment)
                .toList();

        payments.forEach(payment -> {
            PaymentStatus paymentStatus = paymentProviderClient.getPaymentStatus();
            switch (paymentStatus) {
                case COMPLETED -> processCompletePayment(payment);
                case FAILED -> processFailPayment(payment);
            }
        });

    }

    private void processCompletePayment(Payment payment) {
        PaymentState paymentState = PaymentState.builder()
                .paymentId(payment.getId())
                .created(LocalDateTime.now())
                .status(PaymentStatus.COMPLETED)
                .build();

        PaymentStateEntity paymentStateEntity = paymentRepositoryMapper.toPaymentStateEntity(paymentState);
        paymentStateEntityRepository.save(paymentStateEntity);
    }

    private void processFailPayment(Payment payment) {
        walletService.createTopUpWalletTransaction(payment.getUserId(), payment.getAmount() + payment.getFee());
        PaymentState paymentState = PaymentState.builder()
                .paymentId(payment.getId())
                .created(LocalDateTime.now())
                .status(PaymentStatus.REFOUNDED)
                .build();
        PaymentStateEntity paymentStateEntity = paymentRepositoryMapper.toPaymentStateEntity(paymentState);
        paymentStateEntityRepository.save(paymentStateEntity);
    }

    private Payment createPayment(RecipientBankAccount userRecipientBankAccount, WalletTransaction walletTransaction, Double amount) {
        try {
            PaymentProvider paymentProvider = paymentProviderClient.createPaymentProvider(walletTransaction.getWalletBankAccount(),
                    userRecipientBankAccount, amount);

            Payment payment = buildPayment(userRecipientBankAccount, walletTransaction, amount);
            payment.setTransactionId(paymentProvider.getId());


            PaymentEntity paymentEntity = paymentRepositoryMapper.toPaymentEntity(payment);
            paymentEntity.setCreated(LocalDateTime.now());

            PaymentStateEntity paymentStateEntity = paymentRepositoryMapper.toPaymentStateEntityIgnorePayment(PaymentState.builder()
                    .status(PaymentStatus.PROCESSING)
                    .build());
            paymentStateEntity.setCreated(LocalDateTime.now());

            paymentStateEntity.setPayment(paymentEntity);
            paymentEntity.setPaymentStates(List.of(paymentStateEntity));

            PaymentEntity createdPayment = paymentRepository.save(paymentEntity);

            return paymentRepositoryMapper.toPayment(createdPayment);
        } catch (Exception e) {
            revertWithdrawTransaction(userRecipientBankAccount, walletTransaction, amount);
            throw new GeneralErrorWalletException("Unexpected error, it was not possible to process your payment");
        }

    }

    private void revertWithdrawTransaction(RecipientBankAccount userRecipientBankAccount,
                                           WalletTransaction walletTransaction, Double amount) {
        walletService.createTopUpWalletTransaction(walletTransaction.getUserId(), amount);
        Payment errorPayment = buildPayment(userRecipientBankAccount, walletTransaction, amount);


        PaymentEntity paymentEntity = paymentRepositoryMapper.toPaymentEntity(errorPayment);
        paymentEntity.setCreated(LocalDateTime.now());

        PaymentStateEntity paymentStateEntity = paymentRepositoryMapper.toPaymentStateEntityIgnorePayment(PaymentState.builder()
                .status(PaymentStatus.FAILED)
                .build());
        paymentStateEntity.setCreated(LocalDateTime.now());

        paymentStateEntity.setPayment(paymentEntity);
        paymentEntity.setPaymentStates(List.of(paymentStateEntity));

        paymentRepository.save(paymentEntity);
    }

    private Payment buildPayment(RecipientBankAccount userRecipientBankAccount, WalletTransaction walletTransaction,
                                 Double amount) {
        TransactionFee fee = feeService.calculateFee(amount);
        return Payment.builder()
                .userId(walletTransaction.getUserId())
                .amount(fee.getFinalAmount())
                .bankAccount(userRecipientBankAccount)
                .walletTransaction(walletTransaction)
                .fee(fee.getTransactionFee())
                .build();
    }

    private void checkUserFounds(Long userId, Double amount) {
        Double userBalance = walletService.getWalletBalance(userId);
        if (userBalance < amount)
            throw new InvalidPaymentException("insufficient funds");
    }

    private static void checkValidPaymentAmount(Double amount) {
        if (amount <= 0)
            throw new InvalidPaymentException("To process a payment the amount for this transaction has to be upper then 0");
    }

    private static void checkValidPaymentUser(Long userId) {
        if (userId <= 0)
            throw new InvalidPaymentException("To process a payment, it's necessary to provide a user id");
    }

    private RecipientBankAccount getUserBankAccount(Long userId) {
        return recipientBankAccountRepository.findByUserId(userId)
                .map(recipientBankAccountRepositoryMapper::toRecipientBankAccount)
                .orElseThrow(() -> new InvalidBankAccountException(
                        String.format("To process a payment the user with id: %s has to have a registered bank account",
                                userId))
                );
    }
}
