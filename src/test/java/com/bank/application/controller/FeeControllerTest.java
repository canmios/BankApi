package com.bank.application.controller;

import com.bank.application.dto.fee.FeeResponse;
import com.bank.service.fee.FeeService;
import com.bank.service.model.fee.TransactionFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

public class FeeControllerTest {

    @Mock
    private FeeService feeService;

    @InjectMocks
    private FeeController feeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateFee() {
        Double amount = 1000.0;
        Double initialAmount = 1000.0;
        Double transactionFee = 50.0;
        Double finalAmount = 950.0;

        when(feeService.calculateFee(anyDouble()))
                .thenReturn(new TransactionFee(initialAmount, transactionFee, finalAmount));

        FeeResponse feeResponse = feeController.getCostFee(amount);
        assertEquals(initialAmount, feeResponse.getInitialAmount());
        assertEquals(transactionFee, feeResponse.getTransactionFee());
        assertEquals(finalAmount, feeResponse.getFinalAmount());
    }
}