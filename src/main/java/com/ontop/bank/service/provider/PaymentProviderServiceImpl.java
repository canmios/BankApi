package com.ontop.bank.service.provider;

import com.ontop.bank.infrastructure.client.payment.PaymentProviderImpl;
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


    private final PaymentProviderImpl feignPaymentProviderClient;

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
