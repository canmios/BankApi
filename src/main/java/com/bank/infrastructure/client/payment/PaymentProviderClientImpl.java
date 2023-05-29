package com.bank.infrastructure.client.payment;

import com.bank.infrastructure.client.payment.dto.request.PaymentDestinationProvider;
import com.bank.infrastructure.client.payment.dto.request.PaymentProviderRequest;
import com.bank.infrastructure.client.payment.dto.request.PaymentSourceProvider;
import com.bank.infrastructure.client.payment.dto.response.PaymentProviderResponse;
import com.bank.infrastructure.client.payment.mapper.PaymentProviderMapper;
import com.bank.service.model.payment.PaymentProvider;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentProviderClientImpl implements PaymentProviderClient{

    private final FeignPaymentProviderClient feignPaymentProviderClient;

    private final PaymentProviderMapper paymentProviderMapper = PaymentProviderMapper.INSTACE;

    @Override
    public PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank, Double amount) {
        PaymentSourceProvider paymentSourceProvider = paymentProviderMapper.toPaymentSourceProvider(originBank);
        PaymentDestinationProvider paymentDestinationProvider = paymentProviderMapper.toPaymentDestinationProvider(recipientBank);

        PaymentProviderRequest paymentProviderRequest = new PaymentProviderRequest(paymentSourceProvider,
                paymentDestinationProvider, amount);

        PaymentProviderResponse response = feignPaymentProviderClient.createPaymentProvider(paymentProviderRequest);

        return paymentProviderMapper.toPaymentProvider(response);
    }
}
