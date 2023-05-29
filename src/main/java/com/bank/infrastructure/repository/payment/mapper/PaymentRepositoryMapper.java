package com.bank.infrastructure.repository.payment.mapper;

import com.bank.infrastructure.repository.payment.entity.PaymentEntity;
import com.bank.infrastructure.repository.payment.entity.PaymentStateEntity;
import com.bank.service.model.payment.Payment;
import com.bank.service.model.payment.PaymentState;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentRepositoryMapper {

    PaymentRepositoryMapper INSTANCE = Mappers.getMapper(PaymentRepositoryMapper.class);

    @Mapping(target = "paymentStates", ignore = true)
    PaymentEntity toPaymentEntity(Payment payment);

    @Mapping(target = "payment", ignore = true)
    PaymentStateEntity toPaymentStateEntityIgnorePayment(PaymentState paymentState);

    @IterableMapping(qualifiedByName = "mapPaymentStateToPaymentState")
    List<PaymentState> mapPaymentStateListToPaymentStateList(List<PaymentStateEntity> paymentStates);

    @Named("mapPaymentStateToPaymentState")
    default PaymentState mapPaymentStateToPaymentState(PaymentStateEntity paymentStateEntity) {
        PaymentState paymentState = new PaymentState();
        paymentState.setId(paymentStateEntity.getId());
        paymentState.setStatus(paymentStateEntity.getStatus());
        paymentState.setCreated(paymentStateEntity.getCreated());
        return paymentState;
    }

    @Mapping(target = "paymentStates", source = "paymentStates")
    Payment toPayment(PaymentEntity paymentEntity);

    @Mapping(target = "paymentId", source = "payment.id")
    PaymentState toPaymentState(PaymentStateEntity paymentStateEntity);

    @Mapping(target = "payment.id", source = "paymentId")
    PaymentStateEntity toPaymentStateEntity(PaymentState paymentState);


}
