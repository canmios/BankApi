package com.ontop.bank.infrastructure.repository.wallet;

import com.ontop.bank.infrastructure.repository.wallet.mapper.WalletBankAccountRepositoryMapper;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class WalletBankAccountRepositoryImpl implements WalletBankAccountRepository {

    private final WalletBankAccountEntityRepository walletBankAccountEntityRepository;

    private static final Long CURRENT_WALLET_BANK_ACCOUNT_ID = 1L;

    private final WalletBankAccountRepositoryMapper walletBankAccountRepositoryMapper
            = WalletBankAccountRepositoryMapper.INSTANCE;

    @Override
    public Optional<WalletBankAccount> getCurrentWalletBankAccount() {
        return walletBankAccountEntityRepository.findById(CURRENT_WALLET_BANK_ACCOUNT_ID)
                .map(walletBankAccountRepositoryMapper::toWalletBankAccount);
    }
}
