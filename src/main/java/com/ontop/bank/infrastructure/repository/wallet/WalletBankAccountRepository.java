package com.ontop.bank.infrastructure.repository.wallet;

import com.ontop.bank.service.model.wallet.WalletBankAccount;

import java.util.Optional;

public interface WalletBankAccountRepository {

    Optional<WalletBankAccount> getCurrentWalletBankAccount();

}
