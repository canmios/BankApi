package com.ontop.bank.infrastructure.client.payment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentProviderRequest implements Serializable {

    private PaymentSourceProvider source;

    private PaymentDestinationProvider destination;

    private Double amount;

}
