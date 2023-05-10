package com.ontop.bank.infrastructure.client.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentDestinationProvider implements Serializable {

    private String name;
    private PaymentAccountData account;

}
