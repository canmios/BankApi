package com.bank.service.recipient;

import com.bank.service.model.recipient.RecipientBankAccount;

public interface RecipientBankAccountService {

    RecipientBankAccount createBankAccount(RecipientBankAccount recipientBankAccount);

    RecipientBankAccount getBankAccountByUserId(Long userId);

}
