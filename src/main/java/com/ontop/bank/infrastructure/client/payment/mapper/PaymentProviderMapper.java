package com.ontop.bank.infrastructure.client.payment.mapper;

import com.ontop.bank.infrastructure.client.payment.dto.request.PaymentDestinationProvider;
import com.ontop.bank.infrastructure.client.payment.dto.request.PaymentSourceProvider;
import com.ontop.bank.infrastructure.client.payment.dto.response.PaymentProviderResponse;
import com.ontop.bank.service.model.payment.PaymentProvider;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.model.wallet.WalletBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentProviderMapper {

    PaymentProviderMapper INSTACE = Mappers.getMapper(PaymentProviderMapper.class);

    @Mapping(target = "type", source = "type")
    @Mapping(target = "sourceInformation.name", source = "name")
    @Mapping(target = "account.accountNumber", source = "accountNumber")
    @Mapping(target = "account.currency", source = "currency")
    @Mapping(target = "account.routingNumber", source = "routingNumber")
    PaymentSourceProvider toPaymentSourceProvider(WalletBankAccount walletBankAccount);

    @Mapping(target = "name", source = "bankName")
    @Mapping(target = "account.accountNumber", source = "accountNumber")
    @Mapping(target = "account.currency", source = "currency")
    @Mapping(target = "account.routingNumber", source = "routingNumber")
    PaymentDestinationProvider toPaymentDestinationProvider(RecipientBankAccount recipientBankAccount);

    @Mapping(target = "id", source = "paymentInfo.id")
    @Mapping(target = "amount", source = "paymentInfo.amount")
    @Mapping(target = "status", source = "requestInfo.status")
    PaymentProvider toPaymentProvider(PaymentProviderResponse paymentProviderResponse);

}
