package com.ontop.bank.service.fee;

import com.ontop.bank.service.model.fee.TransactionFee;

public interface FeeService {

    TransactionFee calculateFee(Double amount);

}
