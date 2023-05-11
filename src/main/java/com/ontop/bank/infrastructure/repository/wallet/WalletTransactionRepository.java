package com.ontop.bank.infrastructure.repository.wallet;

import com.ontop.bank.service.model.wallet.WalletTransaction;

public interface WalletTransactionRepository {

    WalletTransaction save(WalletTransaction walletTransaction);

}
