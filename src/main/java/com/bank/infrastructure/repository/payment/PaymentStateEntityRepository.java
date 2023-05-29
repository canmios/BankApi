package com.bank.infrastructure.repository.payment;


import com.bank.infrastructure.repository.payment.entity.PaymentStateEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentStateEntityRepository extends CrudRepository<PaymentStateEntity, Long> {
}
