package com.ontop.bank.infrastructure.repository.recipient.mapper;

import com.ontop.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientBankAccountRepositoryMapper {

    RecipientBankAccountRepositoryMapper INSTANCE = Mappers.getMapper(RecipientBankAccountRepositoryMapper.class);

    RecipientBankAccountEntity toRecipientBankAccountEntity(RecipientBankAccount recipientBankAccount);

    RecipientBankAccount toRecipientBankAccount(RecipientBankAccountEntity recipientBankAccountEntity);

}
