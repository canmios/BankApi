package com.bank.infrastructure.repository.wallet.entity;

import com.bank.service.model.wallet.WalletTransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "wallet_transaction")
public class WalletTransactionEntity {

    @Id
    private String id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount")
    private Double amount;

    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private WalletTransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "wallet_bank_account_id")
    private WalletBankAccountEntity walletBankAccount;

}