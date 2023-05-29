package com.bank.infrastructure.repository.wallet;

import com.bank.infrastructure.repository.wallet.entity.WalletTransactionEntity;
import com.bank.infrastructure.repository.wallet.mapper.WalletTransactionRepositoryMapper;
import com.bank.service.model.wallet.WalletTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class WalletTransactionRepositoryImpl implements WalletTransactionRepository {

    private final WalletTransactionEntityRepository walletTransactionEntityRepository;

    private final WalletTransactionRepositoryMapper walletTransactionRepositoryMapper
            = WalletTransactionRepositoryMapper.INSTANCE;


    @Override
    @Transactional
    public WalletTransaction save(WalletTransaction walletTransaction) {
        WalletTransactionEntity walletTransactionEntity = walletTransactionRepositoryMapper
                .toWalletTransactionEntity(walletTransaction);

        return walletTransactionRepositoryMapper
                .toWalletTransaction(walletTransactionEntityRepository.save(walletTransactionEntity));
    }
}
