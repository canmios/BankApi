package com.ontop.bank.infrastructure.repository.wallet;


import com.ontop.bank.infrastructure.entity.WalletTransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface WalletTransactionEntityRepository extends CrudRepository<WalletTransactionEntity, String> {
}
