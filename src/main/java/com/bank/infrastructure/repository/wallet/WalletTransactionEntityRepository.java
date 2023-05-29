package com.bank.infrastructure.repository.wallet;


import com.bank.infrastructure.repository.wallet.entity.WalletTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletTransactionEntityRepository extends CrudRepository<WalletTransactionEntity, String> {
}
