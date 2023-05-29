package com.bank.service.recipient;

import com.bank.infrastructure.repository.recipient.RecipientBankAccountRepository;
import com.bank.service.exception.AccountNotFoundException;
import com.bank.service.exception.InvalidBankAccountException;
import com.bank.service.model.recipient.RecipientBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecipientBankAccountServiceImpl implements RecipientBankAccountService {

    private final RecipientBankAccountRepository recipientBankAccountRepository;

    @Override
    public RecipientBankAccount createBankAccount(RecipientBankAccount recipientBankAccount) {
        validBankAccount(recipientBankAccount);
        return recipientBankAccountRepository.save(recipientBankAccount);
    }

    @Override
    public RecipientBankAccount getBankAccountByUserId(Long userId) {
        return recipientBankAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFoundException("The user does not have a registered bank account"));
    }

    private void validBankAccount(RecipientBankAccount recipientBankAccount) {

        if(recipientBankAccount == null)
            throw new InvalidBankAccountException("To create an bank account it's mandatory provide bank account data");

        recipientBankAccountRepository.findByUserId(recipientBankAccount.getUserId()).ifPresent(it -> {
            throw new InvalidBankAccountException(String.format("The user with id %s already has a registered bank account.", recipientBankAccount.getUserId()));
        });
    }

}
