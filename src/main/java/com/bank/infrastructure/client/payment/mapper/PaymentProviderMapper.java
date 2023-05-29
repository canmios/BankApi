package com.bank.infrastructure.client.payment.mapper;

import com.bank.infrastructure.client.payment.dto.request.PaymentDestinationProvider;
import com.bank.infrastructure.client.payment.dto.request.PaymentSourceProvider;
import com.bank.infrastructure.client.payment.dto.response.PaymentProviderResponse;
import com.bank.service.model.payment.PaymentProvider;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.model.wallet.WalletBankAccount;
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
