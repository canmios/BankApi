package com.bank.infrastructure.repository.recipient;

import com.bank.infrastructure.repository.recipient.mapper.RecipientBankAccountRepositoryMapper;
import com.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import com.bank.service.model.recipient.RecipientBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class RecipientBankAccountRepositoryImpl implements RecipientBankAccountRepository {

    private final RecipientBankAccountEntityRepository bankAccountRepository;

    private final RecipientBankAccountRepositoryMapper recipientBankAccountRepositoryMapper
            = RecipientBankAccountRepositoryMapper.INSTANCE;


    @Override
    public RecipientBankAccount save(RecipientBankAccount recipientBankAccount) {
        RecipientBankAccountEntity recipientBankAccountEntity = recipientBankAccountRepositoryMapper.toRecipientBankAccountEntity(recipientBankAccount);
        RecipientBankAccountEntity recipientBankAccountEntitySaved = bankAccountRepository.save(recipientBankAccountEntity);
        return recipientBankAccountRepositoryMapper.toRecipientBankAccount(recipientBankAccountEntitySaved);
    }

    @Override
    public Optional<RecipientBankAccount> findByUserId(Long userId) {
        return bankAccountRepository.findByUserId(userId)
                .map(recipientBankAccountRepositoryMapper::toRecipientBankAccount);
    }

}
