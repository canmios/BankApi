package com.bank.infrastructure.client.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProviderInfo  implements Serializable {

    private Double amount;
    private String id;
}
