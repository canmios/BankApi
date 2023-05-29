package com.bank.service.model.payment;

import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payment implements Serializable  {

    private Long id;
    private String transactionId;

    private RecipientBankAccount bankAccount;

    private Long userId;

    private Double amount;

    private Double fee;

    private LocalDateTime created;

    private WalletTransaction walletTransaction;

    private List<PaymentState> paymentStates;



}
