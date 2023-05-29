package com.bank.application.dto.payment;

import com.bank.service.model.payment.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    @JsonProperty("payment_id")
    private Long id;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("recipient_bank_account_id")
    private Long recipientBankAccountId;

    private PaymentStatus status;

    private Double amount;

    private Double fee;

    @JsonProperty("user_id")
    private Long userId;

    private LocalDateTime created;

}
