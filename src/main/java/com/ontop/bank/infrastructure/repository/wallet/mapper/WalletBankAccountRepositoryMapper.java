package com.ontop.bank.infrastructure.repository.wallet.mapper;

import com.ontop.bank.infrastructure.entity.WalletBankAccountEntity;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletBankAccountRepositoryMapper {

    WalletBankAccountRepositoryMapper INSTANCE = Mappers.getMapper(WalletBankAccountRepositoryMapper.class);

    WalletBankAccount toWalletBankAccount(WalletBankAccountEntity walletBankAccountEntity);

}
