package com.bank.infrastructure.client.payment;

import com.bank.service.model.payment.PaymentProvider;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletBankAccount;

public interface PaymentProviderClient {

    PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                          Double amount);

}
