package com.bank.infrastructure.repository.wallet;

import com.bank.service.model.wallet.WalletTransaction;

public interface WalletTransactionRepository {

    WalletTransaction save(WalletTransaction walletTransaction);

}
