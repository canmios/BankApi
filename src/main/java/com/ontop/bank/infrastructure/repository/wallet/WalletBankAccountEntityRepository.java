package com.ontop.bank.infrastructure.repository.wallet;


import com.ontop.bank.infrastructure.entity.WalletBankAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletBankAccountEntityRepository extends CrudRepository<WalletBankAccountEntity, Long> {
}
