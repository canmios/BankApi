package com.bank.infrastructure.client.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentAccountData implements Serializable {

    private String accountNumber;
    private String currency;
    private String routingNumber;

}
