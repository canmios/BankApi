package com.bank.infrastructure.repository.payment;

import com.bank.infrastructure.repository.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentEntityRepository extends CrudRepository<PaymentEntity, Long> {

    @Query(value = "SELECT p.* FROM payment p " +
            "JOIN payment_state ps ON p.id = ps.payment_id " +
            "WHERE ps.id = (SELECT ps2.id FROM payment_state ps2 WHERE ps2.payment_id = p.id ORDER BY ps2.created DESC LIMIT 1) " +
            "AND ps.status = 'PROCESSING'", nativeQuery = true)
    List<PaymentEntity> getAllPendingPayments();

    @Query(nativeQuery = true,
            value = "SELECT p.* FROM payment p "
                    + "WHERE (:amount IS NULL OR p.amount = :amount) "
                    + "  AND (cast(:createdFrom as timestamp) IS NULL OR p.created >= :createdFrom) "
                    + "  AND (cast(:createdTo as timestamp) IS NULL OR p.created <= :createdTo) "
                    + "  AND p.user_id = :userId "
                    + "ORDER BY p.created DESC "
                    + "LIMIT :pageSize OFFSET :offset")
    List<PaymentEntity> findPayments(
            @Param("amount") Double amount,
            @Param("createdFrom") LocalDateTime createdFrom,
            @Param("createdTo") LocalDateTime createdTo,
            @Param("userId") Long userId,
            @Param("pageSize") int pageSize,
            @Param("offset") int offset);


}
