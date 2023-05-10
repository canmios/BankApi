package com.ontop.bank.service.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalletBankAccount implements Serializable {

    private Long id;
    private String type;
    private String name;

    private String accountNumber;

    private String currency;

    private String routingNumber;


}
