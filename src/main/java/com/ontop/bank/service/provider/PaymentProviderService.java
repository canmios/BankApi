package com.ontop.bank.service.provider;

import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.payment.PaymentStatus;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.model.wallet.WalletBankAccount;

public interface PaymentProviderService {

    PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                          Double amount);

    PaymentStatus getPaymentStatus();

}