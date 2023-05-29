package com.bank.infrastructure.repository.wallet.mapper;

import com.bank.infrastructure.repository.wallet.entity.WalletBankAccountEntity;
import com.bank.service.model.wallet.WalletBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletBankAccountRepositoryMapper {

    WalletBankAccountRepositoryMapper INSTANCE = Mappers.getMapper(WalletBankAccountRepositoryMapper.class);

    WalletBankAccount toWalletBankAccount(WalletBankAccountEntity walletBankAccountEntity);

}
