package com.ontop.bank.infrastructure.client.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProviderRequestInfo  implements Serializable {

    private String status;
}
