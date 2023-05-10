package com.ontop.bank.infrastructure.repository.recipient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "recipient_bank_account")
public class RecipientBankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "routing_number", nullable = false)
    private String routingNumber;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "holder_first_name", nullable = false)
    private String holderFirstName;

    @Column(name = "holder_last_name", nullable = false)
    private String holderLastName;

    @Column(name = "holder_national_identification_number", nullable = false)
    private Long holderNationalIdentificationNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "currency", nullable = false)
    private String currency;

}
