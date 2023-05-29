package com.bank.application.controller;

import com.bank.application.dto.recipient.RecipientBankAccountRequest;
import com.bank.application.dto.recipient.RecipientBankAccountResponse;
import com.bank.service.model.recipient.RecipientBankAccount;
import com.bank.service.recipient.RecipientBankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipientBankAccountControllerTest {

    @Mock
    private RecipientBankAccountService recipientBankAccountService;

    @InjectMocks
    private RecipientBankAccountController recipientBankAccountController;

    private long userId;
    private RecipientBankAccountRequest recipientBankAccountRequest;

    @BeforeEach
    public void setup() {
        userId = 1L;

        recipientBankAccountRequest = new RecipientBankAccountRequest();
        recipientBankAccountRequest.setRoutingNumber("123456789");
        recipientBankAccountRequest.setAccountNumber("987654321");
        recipientBankAccountRequest.setBankName("Test Bank");
        recipientBankAccountRequest.setHolderFirstName("John");
        recipientBankAccountRequest.setHolderLastName("Doe");
        recipientBankAccountRequest.setHolderNationalIdentificationNumber(123456789L);
        recipientBankAccountRequest.setCurrency("USD");
    }

    @Test
    void testCreateBankAccount() {
        long userId = 1L;
        RecipientBankAccountRequest request = new RecipientBankAccountRequest();
        request.setRoutingNumber("123456");
        request.setAccountNumber("7890123456");
        request.setBankName("Bank of Ontop");
        request.setHolderFirstName("John");
        request.setHolderLastName("Doe");
        request.setHolderNationalIdentificationNumber(123456789L);
        request.setCurrency("USD");

        RecipientBankAccount account = new RecipientBankAccount();
        account.setId(1L);
        account.setRoutingNumber(request.getRoutingNumber());
        account.setAccountNumber(request.getAccountNumber());
        account.setBankName(request.getBankName());
        account.setHolderFirstName(request.getHolderFirstName());
        account.setHolderLastName(request.getHolderLastName());
        account.setHolderNationalIdentificationNumber(request.getHolderNationalIdentificationNumber());
        account.setUserId(userId);
        account.setCurrency(request.getCurrency());

        RecipientBankAccountResponse response = new RecipientBankAccountResponse();
        response.setId(account.getId());
        response.setRoutingNumber(account.getRoutingNumber());
        response.setAccountNumber(account.getAccountNumber());
        response.setBankName(account.getBankName());
        response.setHolderFirstName(account.getHolderFirstName());
        response.setHolderLastName(account.getHolderLastName());
        response.setHolderNationalIdentificationNumber(account.getHolderNationalIdentificationNumber());
        response.setUserId(account.getUserId());
        response.setCurrency(account.getCurrency());

        when(recipientBankAccountService.createBankAccount(any(RecipientBankAccount.class))).thenReturn(account);

        RecipientBankAccountResponse result = recipientBankAccountController.createBankAccount(userId, request);

        verify(recipientBankAccountService).createBankAccount(any(RecipientBankAccount.class));
        assertEquals(response, result);
    }


    @Test
    public void testGetBankAccount() {
        long userId = 1L;
        RecipientBankAccount bankAccount = new RecipientBankAccount();
        bankAccount.setId(1L);
        bankAccount.setUserId(userId);
        bankAccount.setRoutingNumber("123456789");
        bankAccount.setAccountNumber("123456789");
        bankAccount.setBankName("Test Bank");
        bankAccount.setHolderFirstName("John");
        bankAccount.setHolderLastName("Doe");
        bankAccount.setHolderNationalIdentificationNumber(123456789L);
        bankAccount.setCurrency("USD");
        RecipientBankAccountResponse expectedResponse = new RecipientBankAccountResponse();
        expectedResponse.setId(bankAccount.getId());
        expectedResponse.setUserId(bankAccount.getUserId());
        expectedResponse.setRoutingNumber(bankAccount.getRoutingNumber());
        expectedResponse.setAccountNumber(bankAccount.getAccountNumber());
        expectedResponse.setBankName(bankAccount.getBankName());
        expectedResponse.setHolderFirstName(bankAccount.getHolderFirstName());
        expectedResponse.setHolderLastName(bankAccount.getHolderLastName());
        expectedResponse.setHolderNationalIdentificationNumber(bankAccount.getHolderNationalIdentificationNumber());
        expectedResponse.setCurrency(bankAccount.getCurrency());

        when(recipientBankAccountService.getBankAccountByUserId(userId)).thenReturn(bankAccount);

        RecipientBankAccountResponse actualResponse = recipientBankAccountController.getBankAccount(userId);

        assertEquals(expectedResponse, actualResponse);
        verify(recipientBankAccountService).getBankAccountByUserId(userId);
    }

}
