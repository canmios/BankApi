package com.bank.service.fee;

import com.bank.service.model.fee.TransactionFee;

public interface FeeService {

    TransactionFee calculateFee(Double amount);

}
