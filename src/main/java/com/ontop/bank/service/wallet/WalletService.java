package com.ontop.bank.service.wallet;

import com.ontop.bank.service.model.wallet.WalletTransaction;

public interface WalletService {

    WalletTransaction createWithdrawWalletTransaction(Long userId, Double amount);

    WalletTransaction createTopUpWalletTransaction(Long userId, Double amount);

    Double getWalletBalance(Long userId);
}
