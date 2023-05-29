package com.bank.infrastructure.repository.wallet;

import com.bank.service.model.wallet.WalletBankAccount;

import java.util.Optional;

public interface WalletBankAccountRepository {

    Optional<WalletBankAccount> getCurrentWalletBankAccount();

}
