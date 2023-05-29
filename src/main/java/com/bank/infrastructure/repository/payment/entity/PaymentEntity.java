package com.bank.infrastructure.repository.payment.entity;

import com.bank.infrastructure.repository.wallet.entity.WalletTransactionEntity;
import com.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class PaymentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_bank_account_id")
    private RecipientBankAccountEntity bankAccount;

    @Column(name = "user_id")
    private Long userId;

    private Double amount;

    private Double fee;

    @Column(name = "created")
    private LocalDateTime created;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_transaction_id")
    private WalletTransactionEntity walletTransaction;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.PERSIST)
    private List<PaymentStateEntity> paymentStates;

}