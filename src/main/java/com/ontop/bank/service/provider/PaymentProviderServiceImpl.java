package com.ontop.bank.service.provider;

import com.ontop.bank.infrastructure.client.payment.FeignPaymentProviderClient;
import com.ontop.bank.infrastructure.client.payment.dto.request.PaymentDestinationProvider;
import com.ontop.bank.infrastructure.client.payment.dto.request.PaymentProviderRequest;
import com.ontop.bank.infrastructure.client.payment.dto.request.PaymentSourceProvider;
import com.ontop.bank.infrastructure.client.payment.dto.response.PaymentProviderResponse;
import com.ontop.bank.infrastructure.client.payment.mapper.PaymentProviderMapper;
import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.payment.PaymentStatus;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@Component
public class PaymentProviderServiceImpl implements PaymentProviderService {


    private final FeignPaymentProviderClient feignPaymentProviderClient;


    private final PaymentProviderMapper paymentProviderMapper = PaymentProviderMapper.INSTACE;


    @Override
    public PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                                 Double amount) {
        PaymentSourceProvider paymentSourceProvider = paymentProviderMapper.toPaymentSourceProvider(originBank);
        PaymentDestinationProvider paymentDestinationProvider = paymentProviderMapper.toPaymentDestinationProvider(recipientBank);

        PaymentProviderRequest paymentProviderRequest = new PaymentProviderRequest(paymentSourceProvider,
                paymentDestinationProvider, amount);

        PaymentProviderResponse response = feignPaymentProviderClient.createPaymentProvider(paymentProviderRequest);
        return paymentProviderMapper.toPaymentProvider(response);
    }

    @Override
    public PaymentStatus getPaymentStatus() {
        return getRandomStatus();
    }

    public PaymentStatus getRandomStatus(){
        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 0){
            return PaymentStatus.COMPLETED;
        } else if (random == 1) {
            return PaymentStatus.FAILED;
        }else {
            return PaymentStatus.REFOUNDED;
        }
    }
}
