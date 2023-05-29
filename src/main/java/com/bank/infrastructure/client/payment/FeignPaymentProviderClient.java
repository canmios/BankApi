package com.bank.infrastructure.client.payment;

import com.bank.infrastructure.client.payment.dto.request.PaymentProviderRequest;
import com.bank.infrastructure.client.payment.dto.response.PaymentProviderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-api", url = "http://mockoon.tools.getontop.com:3000/api/v1/payments")
public interface FeignPaymentProviderClient {

    @PostMapping
    PaymentProviderResponse createPaymentProvider(@RequestBody PaymentProviderRequest paymentProviderRequest);

}
