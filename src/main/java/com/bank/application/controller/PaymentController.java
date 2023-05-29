package com.bank.application.controller;


import com.bank.application.dto.payment.PaymentResponse;
import com.bank.application.mapper.PaymentMapper;
import com.bank.service.model.payment.Payment;
import com.bank.application.dto.payment.PaymentRequest;
import com.bank.service.payment.PaymentService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/{user_id}/payments")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentMapper paymentMapper = PaymentMapper.INSTANCE;

    @ApiOperation(value = "isRunning", notes = "Create the transaction of payments")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createPaymentTransaction(@PathVariable("user_id") long userId,
                                                    @RequestBody @Valid PaymentRequest paymentRequest) {

        return paymentMapper.toPaymentTransactionResponse(
                paymentService.createPaymentTransaction(userId, paymentRequest.getAmount()));
    }

    @ApiOperation(value = "isRunning", notes = "Get list of payments, ordered by creation order")
    @GetMapping
    public List<PaymentResponse> getPayments(@PathVariable("user_id") long userId, @RequestParam(required = false) Double amount,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                             LocalDateTime createdFrom,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                 LocalDateTime createdTo,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "0") int offset) {

        List<Payment> payments = paymentService.getAllPayments(userId, amount, createdFrom, createdTo, pageSize, offset);
        return payments.stream().map(paymentMapper::toPaymentTransactionResponse).collect(Collectors.toList());
    }

}
