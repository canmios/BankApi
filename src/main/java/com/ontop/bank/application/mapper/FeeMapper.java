package com.ontop.bank.application.mapper;

import com.ontop.bank.application.dto.fee.FeeResponse;
import com.ontop.bank.service.model.fee.TransactionFee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeeMapper {


    FeeMapper INSTANCE = Mappers.getMapper(FeeMapper.class);

    FeeResponse toTransactionFeeResponse(TransactionFee transactionFee);

}
