package com.bank.infrastructure.client.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentSourceInformationProvider implements Serializable {

    private String name;
}
