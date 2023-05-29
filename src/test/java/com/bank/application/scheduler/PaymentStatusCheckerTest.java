package com.bank.application.scheduler;

import com.bank.service.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentStatusCheckerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentStatusChecker paymentStatusChecker;

    @Test
    public void testProcessPayments() {
        paymentStatusChecker.processPayments();
        verify(paymentService, times(1)).processAllPendingPayments();
    }

}
