package com.ontop.bank.infrastructure.client.wallet;

public interface WalletClient {

    Double getWalletBalance(Long userId);

    Long createWalletTransaction(Long userId, Double amount);
}
