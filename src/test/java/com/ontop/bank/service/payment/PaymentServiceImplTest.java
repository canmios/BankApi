package com.ontop.bank.service.payment;

import com.ontop.bank.infrastructure.repository.payment.PaymentEntityRepository;
import com.ontop.bank.infrastructure.repository.payment.entity.PaymentEntity;
import com.ontop.bank.infrastructure.repository.payment.mapper.PaymentRepositoryMapper;
import com.ontop.bank.infrastructure.repository.recipient.RecipientBankAccountEntityRepository;
import com.ontop.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import com.ontop.bank.service.exception.InvalidBankAccountException;
import com.ontop.bank.service.exception.InvalidPaymentException;
import com.ontop.bank.service.fee.FeeService;
import com.ontop.bank.service.model.fee.TransactionFee;
import com.ontop.bank.service.model.payment.Payment;
import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.wallet.WalletTransaction;
import com.ontop.bank.service.provider.PaymentProviderServiceImpl;
import com.ontop.bank.service.wallet.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private RecipientBankAccountEntityRepository recipientBankAccountRepository;

    @Spy
    private PaymentEntityRepository paymentRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private PaymentProviderServiceImpl paymentProviderClient;

    @Mock
    private FeeService feeService;

    @Mock
    private RecipientBankAccountEntity recipientBankAccountEntity;

    private Long userId;
    private Double amount;

    private WalletTransaction walletTransaction;

    private PaymentEntity payment;
    private TransactionFee transactionFee;

    private final PaymentRepositoryMapper paymentRepositoryMapper = PaymentRepositoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
        userId = 1L;
        amount = 100.0;
        walletTransaction = new WalletTransaction();
        transactionFee = new TransactionFee();
        payment = new PaymentEntity();
        transactionFee.setInitialAmount(amount);
        transactionFee.setTransactionFee(2.0);
        transactionFee.setFinalAmount(98.0);

        PaymentProvider paymentProvider = new PaymentProvider();
        paymentProvider.setId("paymentProviderId");

        when(paymentProviderClient.createPaymentProvider(any(), any(), anyDouble()))
                .thenReturn(paymentProvider);

        when(walletService.getWalletBalance(userId)).thenReturn(amount);
        when(walletService.createWithdrawWalletTransaction(userId, amount)).thenReturn(walletTransaction);
        when(feeService.calculateFee(amount)).thenReturn(transactionFee);
        when(recipientBankAccountRepository.findByUserId(userId)).thenReturn(Optional.of(recipientBankAccountEntity));
        when(paymentRepository.save(any())).thenReturn(payment);

    }

    @Test
    void createPaymentTransaction_success() {
        Payment result = paymentService.createPaymentTransaction(userId, amount);
        assertNotNull(result);
        assertEquals(paymentRepositoryMapper.toPayment(payment), result);
    }

    @Test
    void createPaymentTransaction_insufficientFunds() {
        when(walletService.getWalletBalance(userId)).thenReturn(50.0);
        assertThrows(InvalidPaymentException.class, () -> paymentService.createPaymentTransaction(userId, amount));
    }

    @Test
    void createPaymentTransaction_invalidAmount() {

        assertThrows(InvalidPaymentException.class, () -> paymentService.createPaymentTransaction(userId, -1.0));
    }

    @Test
    void createPaymentTransaction_invalidUser() {
        assertThrows(InvalidPaymentException.class, () -> paymentService.createPaymentTransaction(-1L, amount));
    }

    @Test
    void createPaymentTransaction_noBankAccount() {
        when(recipientBankAccountRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(InvalidBankAccountException.class, () -> paymentService.createPaymentTransaction(userId, amount));
    }

}
