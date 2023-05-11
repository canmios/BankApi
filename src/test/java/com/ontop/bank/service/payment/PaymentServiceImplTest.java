package com.ontop.bank.service.payment;

import com.ontop.bank.infrastructure.repository.payment.PaymentRepository;
import com.ontop.bank.infrastructure.repository.recipient.RecipientBankAccountRepository;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl domainPaymentService;

    @Mock
    private RecipientBankAccountRepository recipientBankAccountRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private PaymentProviderServiceImpl paymentProviderClient;

    @Mock
    private FeeService feeService;

    private Long userId;
    private Double amount;
    private RecipientBankAccount recipientBankAccount;
    private WalletTransaction walletTransaction;
    private Payment payment;
    private TransactionFee transactionFee;

    @BeforeEach
    void setUp() {
        userId = 1L;
        amount = 100.0;
        recipientBankAccount = new RecipientBankAccount();
        walletTransaction = new WalletTransaction();
        payment = new Payment();
        transactionFee = new TransactionFee();

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
        when(recipientBankAccountRepository.findByUserId(userId)).thenReturn(Optional.of(recipientBankAccount));
        when(paymentRepository.save(any(), any())).thenReturn(payment);


    }

    @Test
    void createPaymentTransaction_success() {
        Payment result = domainPaymentService.createPaymentTransaction(userId, amount);
        assertNotNull(result);
        assertEquals(payment, result);
    }

    @Test
    void createPaymentTransaction_insufficientFunds() {
        when(walletService.getWalletBalance(userId)).thenReturn(50.0);
        assertThrows(InvalidPaymentException.class, () -> domainPaymentService.createPaymentTransaction(userId, amount));
    }

    @Test
    void createPaymentTransaction_invalidAmount() {
        assertThrows(InvalidPaymentException.class, () -> domainPaymentService.createPaymentTransaction(userId, -1.0));
    }

    @Test
    void createPaymentTransaction_invalidUser() {
        assertThrows(InvalidPaymentException.class, () -> domainPaymentService.createPaymentTransaction(-1L, amount));
    }

    @Test
    void createPaymentTransaction_noBankAccount() {
        when(recipientBankAccountRepository.findByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(InvalidBankAccountException.class, () -> domainPaymentService.createPaymentTransaction(userId, amount));
    }

    @Test
    void getAllPayments_success() {
        LocalDateTime createdFrom = LocalDateTime.now().minusDays(1);
        LocalDateTime createdTo = LocalDateTime.now();
        int pageSize = 10;
        int offSet = 0;

        List<Payment> expectedPayments = Arrays.asList(new Payment(), new Payment());
        when(paymentRepository.getAllPayments(userId, amount, createdFrom, createdTo, pageSize, offSet))
                .thenReturn(expectedPayments);

        List<Payment> result = domainPaymentService.getAllPayments(userId, amount, createdFrom, createdTo, pageSize, offSet);

        assertEquals(expectedPayments, result);
    }

    @Test
    void processAllPendingPayments_completed() {
        List<Payment> pendingPayments = Collections.singletonList(Payment.builder().transactionId("1").build());
        when(paymentRepository.getAllPendingPayments()).thenReturn(pendingPayments);

        when(paymentProviderClient.getPaymentStatus())
                .thenReturn(PaymentStatus.COMPLETED);

        domainPaymentService.processAllPendingPayments();

        ArgumentCaptor<PaymentState> paymentCaptor = ArgumentCaptor.forClass(PaymentState.class);
        verify(paymentRepository, times(1)).savePaymentState(paymentCaptor.capture());
        List<PaymentState> capturedPayments = paymentCaptor.getAllValues();

        assertEquals(PaymentStatus.COMPLETED, capturedPayments.get(0).getStatus());

    }

    @Test
    void processAllPendingPayments_failed() {
        List<Payment> pendingPayments = Collections.singletonList(Payment.builder().transactionId("1").amount(100.0).fee(10.0).build());
        when(paymentRepository.getAllPendingPayments()).thenReturn(pendingPayments);

        when(paymentProviderClient.getPaymentStatus())
                .thenReturn(PaymentStatus.FAILED);
        when(walletService.createTopUpWalletTransaction(any(), any())).thenReturn(new WalletTransaction());

        domainPaymentService.processAllPendingPayments();

        ArgumentCaptor<PaymentState> paymentCaptor = ArgumentCaptor.forClass(PaymentState.class);
        verify(paymentRepository, times(1)).savePaymentState(paymentCaptor.capture());
        List<PaymentState> capturedPayments = paymentCaptor.getAllValues();

        assertEquals(PaymentStatus.REFOUNDED, capturedPayments.get(0).getStatus());

    }

}
