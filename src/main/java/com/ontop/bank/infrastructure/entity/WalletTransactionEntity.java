package com.ontop.bank.infrastructure.entity;

import com.ontop.bank.service.model.wallet.WalletTransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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