package com.bank.infrastructure.repository.payment;

import com.bank.service.model.payment.Payment;
import com.bank.service.model.payment.PaymentState;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository {

    Payment save(Payment payment, PaymentState paymentState);

    PaymentState savePaymentState(PaymentState paymentState);

    List<Payment> getAllPendingPayments();

    List<Payment> getAllPayments(Long userId,Double amount, LocalDateTime createdFrom, LocalDateTime createdTo,
                                 int pageSize, int offSet);


}
