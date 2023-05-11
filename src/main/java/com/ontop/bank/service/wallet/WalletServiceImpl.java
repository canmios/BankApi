package com.ontop.bank.service.wallet;


import com.ontop.bank.infrastructure.repository.wallet.WalletBankAccountRepository;
import com.ontop.bank.infrastructure.repository.wallet.WalletTransactionRepository;
import com.ontop.bank.service.exception.GeneralErrorWalletException;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import com.ontop.bank.service.model.wallet.WalletTransaction;
import com.ontop.bank.infrastructure.client.wallet.WalletClient;
import com.ontop.bank.service.model.wallet.WalletTransactionType;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletClient walletClient;

    private final WalletTransactionRepository walletTransactionRepository;

    private final WalletBankAccountRepository walletBankAccountRepository;

    @Override
    public WalletTransaction createWithdrawWalletTransaction(Long userId, Double amount) {
        WalletTransaction walletTransaction =
                new WalletTransaction(walletClient.createWalletTransaction(userId, -amount), userId, -amount,
                        LocalDateTime.now(), WalletTransactionType.OUT, getCurrentWalletBankAccount());

        return  walletTransactionRepository.save(walletTransaction);
    }

    @Override
    public WalletTransaction createTopUpWalletTransaction(Long userId, Double amount) {

        WalletTransaction walletTransaction =
                new WalletTransaction(walletClient.createWalletTransaction(userId, amount), userId, amount,
                        LocalDateTime.now(), WalletTransactionType.IN, getCurrentWalletBankAccount());

        return  walletTransactionRepository.save(walletTransaction);
    }


    @Override
    public Double getWalletBalance(Long userId) {
        return walletClient.getWalletBalance(userId);
    }

    private WalletBankAccount getCurrentWalletBankAccount() {
        return walletBankAccountRepository.getCurrentWalletBankAccount()
                .orElseThrow(() -> new GeneralErrorWalletException("Unexpected error. it was not possible getting Wallet Bank Account"));
    }


}
