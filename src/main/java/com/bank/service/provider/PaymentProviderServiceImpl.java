package com.bank.service.provider;

import com.bank.infrastructure.client.payment.PaymentProviderClientImpl;
import com.bank.service.model.payment.PaymentProvider;
import com.bank.service.model.payment.PaymentStatus;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@AllArgsConstructor
@Component
public class PaymentProviderServiceImpl implements PaymentProviderService {


    private final PaymentProviderClientImpl feignPaymentProviderClient;

    @Override
    public PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                                 Double amount) {
        return feignPaymentProviderClient.createPaymentProvider(originBank, recipientBank, amount);
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
