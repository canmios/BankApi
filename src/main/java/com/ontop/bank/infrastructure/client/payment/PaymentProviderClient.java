package com.ontop.bank.infrastructure.client.payment;

import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.model.wallet.WalletBankAccount;

public interface PaymentProviderClient {

    PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                          Double amount);

}
