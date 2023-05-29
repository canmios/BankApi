package com.bank.infrastructure.client.payment.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProviderResponse  implements Serializable {

    private PaymentProviderRequestInfo requestInfo;
    private PaymentProviderInfo paymentInfo;

}
