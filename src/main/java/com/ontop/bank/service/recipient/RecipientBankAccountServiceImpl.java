package com.ontop.bank.service.recipient;

import com.ontop.bank.infrastructure.repository.recipient.RecipientBankAccountEntityRepository;
import com.ontop.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import com.ontop.bank.infrastructure.repository.recipient.mapper.RecipientBankAccountRepositoryMapper;
import com.ontop.bank.service.exception.AccountNotFoundException;
import com.ontop.bank.service.exception.InvalidBankAccountException;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import org.springframework.stereotype.Service;

@Service
public class RecipientBankAccountServiceImpl implements RecipientBankAccountService {

    private final RecipientBankAccountEntityRepository bankAccountRepository;

    private final RecipientBankAccountRepositoryMapper recipientBankAccountRepositoryMapper
            = RecipientBankAccountRepositoryMapper.INSTANCE;

    public RecipientBankAccountServiceImpl(RecipientBankAccountEntityRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public RecipientBankAccount createBankAccount(RecipientBankAccount recipientBankAccount) {
        validBankAccount(recipientBankAccount);
        RecipientBankAccountEntity recipientBankAccountEntity = recipientBankAccountRepositoryMapper.toRecipientBankAccountEntity(recipientBankAccount);
        RecipientBankAccountEntity recipientBankAccountEntitySaved = bankAccountRepository.save(recipientBankAccountEntity);
        return recipientBankAccountRepositoryMapper.toRecipientBankAccount(recipientBankAccountEntitySaved);
    }

    @Override
    public RecipientBankAccount getBankAccountByUserId(Long userId) {
        return bankAccountRepository.findByUserId(userId)
                .map(recipientBankAccountRepositoryMapper::toRecipientBankAccount)
                .orElseThrow(() -> new AccountNotFoundException("The user does not have a registered bank account"));
    }

    private void validBankAccount(RecipientBankAccount recipientBankAccount) {

        if(recipientBankAccount == null)
            throw new InvalidBankAccountException("To create an bank account it's mandatory provide bank account data");

        bankAccountRepository.findByUserId(recipientBankAccount.getUserId())
                .map(recipientBankAccountRepositoryMapper::toRecipientBankAccount).ifPresent(it -> {
                    throw new InvalidBankAccountException(String.format("The user with id %s already has a registered bank account.", recipientBankAccount.getUserId()));
                });
    }

}
