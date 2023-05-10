package com.ontop.bank.application.mapper;

import com.ontop.bank.application.dto.recipient.RecipientBankAccountRequest;
import com.ontop.bank.application.dto.recipient.RecipientBankAccountResponse;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
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
