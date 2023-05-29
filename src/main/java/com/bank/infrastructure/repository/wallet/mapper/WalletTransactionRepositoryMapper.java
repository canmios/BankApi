package com.bank.infrastructure.repository.wallet.mapper;

import com.bank.infrastructure.repository.wallet.entity.WalletTransactionEntity;
import com.bank.service.model.wallet.WalletTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletTransactionRepositoryMapper {

    WalletTransactionRepositoryMapper INSTANCE = Mappers.getMapper(WalletTransactionRepositoryMapper.class);

    WalletTransactionEntity toWalletTransactionEntity(WalletTransaction walletTransaction);

    WalletTransaction toWalletTransaction(WalletTransactionEntity walletTransactionEntity);

}
