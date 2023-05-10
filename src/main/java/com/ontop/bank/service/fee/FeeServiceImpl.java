package com.ontop.bank.service.fee;


import com.ontop.bank.service.model.fee.TransactionFee;

public class FeeServiceImpl implements FeeService{
    @Override
    public TransactionFee calculateFee(Double amount) {
         Double fee = amount*0.10;
         return new TransactionFee(amount, fee, amount-fee);
    }
}
