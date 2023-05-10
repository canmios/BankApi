package com.ontop.bank.service.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalletTransaction implements Serializable {

    private Long id;

    private Long userId;

    private Double amount;

    private LocalDateTime created;

    private WalletTransactionType transactionType;

    private WalletBankAccount walletBankAccount;

}
