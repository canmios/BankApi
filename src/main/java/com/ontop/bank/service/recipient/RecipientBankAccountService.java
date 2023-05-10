package com.ontop.bank.service.recipient;

import com.ontop.bank.service.model.recipient.RecipientBankAccount;

public interface RecipientBankAccountService {

    RecipientBankAccount createBankAccount(RecipientBankAccount recipientBankAccount);

    RecipientBankAccount getBankAccountByUserId(Long userId);

}
