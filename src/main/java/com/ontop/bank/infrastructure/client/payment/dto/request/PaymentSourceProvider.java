package com.ontop.bank.infrastructure.client.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentSourceProvider implements Serializable {

    private String type;

    private PaymentSourceInformationProvider sourceInformation;
    private PaymentAccountData account;

}
