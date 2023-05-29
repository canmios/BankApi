package com.bank.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.bank.application.dto.payment.PaymentRequest;
import com.bank.application.dto.payment.PaymentResponse;
import com.bank.application.mapper.PaymentMapper;
import com.bank.service.model.payment.Payment;
import com.bank.service.model.payment.PaymentState;
import com.bank.service.model.payment.PaymentStatus;
import com.bank.service.payment.PaymentService;
import org.junit.jupiter.api.*;

public class PaymentControllerTest {

    private PaymentService paymentService;

    private PaymentMapper paymentMapper;

    private PaymentController paymentController;

    private long userId;
    private PaymentRequest paymentRequest;

    @BeforeEach
    public void setup() {
        userId = 1L;
        paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100.0);

        paymentService = mock(PaymentService.class);
        paymentMapper = mock(PaymentMapper.class);

        paymentController = new PaymentController(paymentService);

    }

    @Test
    public void testCreatePaymentTransaction() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setTransactionId("TX001");
        payment.setUserId(userId);
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStates(Collections.singletonList(new PaymentState(1L,1L, PaymentStatus.COMPLETED, LocalDateTime.now() )));

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setTransactionId(payment.getTransactionId());
        paymentResponse.setUserId(payment.getUserId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setStatus(PaymentStatus.COMPLETED);

        when(paymentService.createPaymentTransaction(userId, paymentRequest.getAmount())).thenReturn(payment);
        lenient().when(paymentMapper.toPaymentTransactionResponse(payment)).thenReturn(paymentResponse);

        PaymentResponse result = paymentController.createPaymentTransaction(userId, paymentRequest);
        assertEquals(paymentResponse, result);

        verify(paymentService).createPaymentTransaction(userId, paymentRequest.getAmount());
    }

    @Test
    public void testGetPayments() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setTransactionId("TX001");
        payment.setUserId(userId);
        payment.setAmount(paymentRequest.getAmount());
        payment.setCreated(LocalDateTime.now());
        payment.setPaymentStates(Collections.singletonList(new PaymentState(1L,1L, PaymentStatus.COMPLETED, LocalDateTime.now() )));


        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setTransactionId(payment.getTransactionId());
        paymentResponse.setUserId(payment.getUserId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setCreated(payment.getCreated());
        paymentResponse.setStatus(PaymentStatus.COMPLETED);

        when(paymentService.getAllPayments(userId, paymentRequest.getAmount(), null, null, 10, 0)).thenReturn(Collections.singletonList(payment));
        lenient().when(paymentMapper.toPaymentTransactionResponse(payment)).thenReturn(paymentResponse);

        List<PaymentResponse> result = paymentController.getPayments(userId, paymentRequest.getAmount(), null, null, 10, 0);
        assertEquals(Collections.singletonList(paymentResponse), result);

        verify(paymentService).getAllPayments(userId, paymentRequest.getAmount(), null, null, 10, 0);

    }

}
