package com.bank.infrastructure.repository.recipient;

import com.bank.service.model.recipient.RecipientBankAccount;

import java.util.Optional;

public interface RecipientBankAccountRepository {

    RecipientBankAccount save(RecipientBankAccount recipientBankAccount);

    Optional<RecipientBankAccount> findByUserId(Long userId);

}
