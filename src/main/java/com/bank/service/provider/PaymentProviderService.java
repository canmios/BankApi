package com.bank.service.provider;

import com.bank.service.model.payment.PaymentProvider;
import com.bank.service.model.payment.PaymentStatus;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletBankAccount;

public interface PaymentProviderService {

    PaymentProvider createPaymentProvider(WalletBankAccount originBank, RecipientBankAccount recipientBank,
                                          Double amount);

    PaymentStatus getPaymentStatus();

}