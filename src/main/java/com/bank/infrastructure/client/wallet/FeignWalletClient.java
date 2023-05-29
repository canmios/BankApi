package com.bank.infrastructure.client.wallet;

import com.bank.infrastructure.client.wallet.dto.request.WalletTransactionRequest;
import com.bank.infrastructure.client.wallet.dto.response.WalletTransactionResponse;
import com.bank.infrastructure.client.wallet.dto.response.WalletUserBalanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wallet-api", url = "http://mockoon.tools.getontop.com:3000/wallets")
public interface FeignWalletClient {

    @GetMapping("/balance")
    WalletUserBalanceResponse getWalletUserBalance(@RequestParam ("user_id") Long userId);

    @PostMapping("/transactions")
    WalletTransactionResponse createWalletTransaction(@RequestBody WalletTransactionRequest wallet);

}
