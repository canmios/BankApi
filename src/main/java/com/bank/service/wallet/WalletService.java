package com.bank.service.wallet;

import com.bank.service.model.wallet.WalletTransaction;

public interface WalletService {

    WalletTransaction createWithdrawWalletTransaction(Long userId, Double amount);

    WalletTransaction createTopUpWalletTransaction(Long userId, Double amount);

    Double getWalletBalance(Long userId);
}
