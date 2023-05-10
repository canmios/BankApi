package com.ontop.bank.infrastructure.repository.wallet.mapper;

import com.ontop.bank.infrastructure.entity.WalletTransactionEntity;
import com.ontop.bank.service.model.wallet.WalletTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletTransactionRepositoryMapper {

    WalletTransactionRepositoryMapper INSTANCE = Mappers.getMapper(WalletTransactionRepositoryMapper.class);

    WalletTransactionEntity toWalletTransactionEntity(WalletTransaction walletTransaction);

    WalletTransaction toWalletTransaction(WalletTransactionEntity walletTransactionEntity);

}
