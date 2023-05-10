package com.ontop.bank.infrastructure.client.wallet;

import com.ontop.bank.infrastructure.client.wallet.dto.request.WalletTransactionRequest;
import com.ontop.bank.infrastructure.client.wallet.dto.response.WalletUserBalanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class WalletClientImpl implements WalletClient {

    private final FeignWalletClient feignWalletClient;

    @Override
    public Double getWalletBalance(Long userId) {
        WalletUserBalanceResponse walletUserBalanceResponse = feignWalletClient.getWalletUserBalance(userId);
        return walletUserBalanceResponse.getBalance();
    }

    @Override
    public Long createWalletTransaction(Long userId, Double amount) {
        return feignWalletClient.createWalletTransaction(new WalletTransactionRequest(userId, amount))
                .getWalletTransactionId();
    }


}
