package com.ontop.bank.service.model.recipient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecipientBankAccount implements Serializable {
    private Long id;
    private String routingNumber;
    private String accountNumber;

    private String bankName;

    private String holderFirstName;

    private String holderLastName;

    private Long holderNationalIdentificationNumber;

    private Long userId;

    private String currency;

}
