package com.bank.infrastructure.repository.wallet;


import com.bank.infrastructure.repository.wallet.entity.WalletBankAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletBankAccountEntityRepository extends CrudRepository<WalletBankAccountEntity, Long> {
}
