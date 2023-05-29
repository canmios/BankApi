package com.bank.application.mapper;

import com.bank.application.dto.recipient.RecipientBankAccountRequest;
import com.bank.application.dto.recipient.RecipientBankAccountResponse;
import com.bank.service.model.recipient.RecipientBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientBankAccountMapper {

    RecipientBankAccountMapper INSTANCE = Mappers.getMapper(RecipientBankAccountMapper.class);

    RecipientBankAccountResponse toBankAccountResponse(RecipientBankAccount recipientBankAccount);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "id", ignore = true)
    RecipientBankAccount toBankAccount(RecipientBankAccountRequest recipientBankAccountRequest);

}
