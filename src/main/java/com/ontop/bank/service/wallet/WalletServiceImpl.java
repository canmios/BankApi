package com.ontop.bank.service.wallet;


import com.ontop.bank.infrastructure.entity.WalletTransactionEntity;
import com.ontop.bank.infrastructure.repository.wallet.WalletBankAccountEntityRepository;
import com.ontop.bank.infrastructure.repository.wallet.WalletTransactionEntityRepository;
import com.ontop.bank.infrastructure.repository.wallet.mapper.WalletBankAccountRepositoryMapper;
import com.ontop.bank.infrastructure.repository.wallet.mapper.WalletTransactionRepositoryMapper;
import com.ontop.bank.service.exception.GeneralErrorWalletException;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import com.ontop.bank.service.model.wallet.WalletTransaction;
import com.ontop.bank.infrastructure.client.wallet.WalletClient;
import com.ontop.bank.service.model.wallet.WalletTransactionType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletClient walletClient;

    private final WalletTransactionEntityRepository walletTransactionEntityRepository;

    private final WalletBankAccountEntityRepository walletBankAccountEntityRepository;

    private static final Long CURRENT_WALLET_BANK_ACCOUNT_ID = 1L;


    private final WalletTransactionRepositoryMapper walletTransactionRepositoryMapper
            = WalletTransactionRepositoryMapper.INSTANCE;
    private final WalletBankAccountRepositoryMapper walletBankAccountRepositoryMapper
            = WalletBankAccountRepositoryMapper.INSTANCE;

    public WalletServiceImpl(WalletClient walletClient, WalletTransactionEntityRepository walletTransactionEntityRepository,
                             WalletBankAccountEntityRepository walletBankAccountEntityRepository) {
        this.walletClient = walletClient;
        this.walletTransactionEntityRepository = walletTransactionEntityRepository;
        this.walletBankAccountEntityRepository = walletBankAccountEntityRepository;
    }

    @Override
    public WalletTransaction createWithdrawWalletTransaction(Long userId, Double amount) {
        WalletTransaction walletTransaction =
                new WalletTransaction(walletClient.createWalletTransaction(userId, -amount), userId, -amount,
                        LocalDateTime.now(), WalletTransactionType.OUT, getCurrentWalletBankAccount());

        WalletTransactionEntity walletTransactionEntity = walletTransactionRepositoryMapper
                .toWalletTransactionEntity(walletTransaction);

        return walletTransactionRepositoryMapper
                .toWalletTransaction(walletTransactionEntityRepository.save(walletTransactionEntity));
    }

    @Override
    public WalletTransaction createTopUpWalletTransaction(Long userId, Double amount) {

        WalletTransaction walletTransaction =
                new WalletTransaction(walletClient.createWalletTransaction(userId, amount), userId, amount,
                        LocalDateTime.now(), WalletTransactionType.IN, getCurrentWalletBankAccount());

        WalletTransactionEntity walletTransactionEntity = walletTransactionRepositoryMapper
                .toWalletTransactionEntity(walletTransaction);

        return walletTransactionRepositoryMapper
                .toWalletTransaction(walletTransactionEntityRepository.save(walletTransactionEntity));
    }


    @Override
    public Double getWalletBalance(Long userId) {
        return walletClient.getWalletBalance(userId);
    }

    private WalletBankAccount getCurrentWalletBankAccount() {
        return walletBankAccountEntityRepository.findById(CURRENT_WALLET_BANK_ACCOUNT_ID)
                .map(walletBankAccountRepositoryMapper::toWalletBankAccount)
                .orElseThrow(() ->
                        new GeneralErrorWalletException("Unexpected error. it was not possible getting Wallet Bank Account"));

    }


}
