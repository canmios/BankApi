package com.ontop.bank.service.fee;

import com.ontop.bank.service.model.fee.TransactionFee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FeeServiceImplTest {

    @Test
    public void testCalculateFee() {
        // arrange
        FeeService feeService = new FeeServiceImpl();
        Double initialAmount = 100.0;

        // act
        TransactionFee transactionFee = feeService.calculateFee(initialAmount);

        // assert
        assertNotNull(transactionFee);
        assertEquals(initialAmount, transactionFee.getInitialAmount());
        assertEquals(initialAmount*0.10, transactionFee.getTransactionFee());
        assertEquals(initialAmount - (initialAmount*0.10), transactionFee.getFinalAmount());
    }
}
