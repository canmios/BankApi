package com.ontop.bank.infrastructure.repository.wallet;

import com.ontop.bank.infrastructure.entity.WalletTransactionEntity;
import com.ontop.bank.infrastructure.repository.wallet.mapper.WalletTransactionRepositoryMapper;
import com.ontop.bank.service.model.wallet.WalletTransaction;
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
