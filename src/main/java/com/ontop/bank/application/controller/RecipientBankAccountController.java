package com.ontop.bank.application.controller;

import com.ontop.bank.application.dto.recipient.RecipientBankAccountRequest;
import com.ontop.bank.application.dto.recipient.RecipientBankAccountResponse;
import com.ontop.bank.application.mapper.RecipientBankAccountMapper;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import com.ontop.bank.service.recipient.RecipientBankAccountService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users/{user_id}/recipient-bank-accounts")
public class RecipientBankAccountController {

    private final RecipientBankAccountService recipientBankAccountService;

    private final RecipientBankAccountMapper bankAccountRequestMapper = RecipientBankAccountMapper.INSTANCE;

    @ApiOperation(value = "isRunning", notes = "Create the bank account where the transfer is made")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipientBankAccountResponse createBankAccount(@PathVariable("user_id") long userId,
                                                          @RequestBody @Valid RecipientBankAccountRequest recipientBankAccountRequest) {
        RecipientBankAccount recipientBankAccount = bankAccountRequestMapper.toBankAccount(recipientBankAccountRequest);
        recipientBankAccount.setUserId(userId);

        return bankAccountRequestMapper.toBankAccountResponse(recipientBankAccountService.createBankAccount(recipientBankAccount));
    }

    @ApiOperation(value = "isRunning", notes = "Get the bank account of the user")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RecipientBankAccountResponse getBankAccount(@PathVariable("user_id") long userId) {
        RecipientBankAccount recipientBankAccount = recipientBankAccountService.getBankAccountByUserId(userId);
        return bankAccountRequestMapper.toBankAccountResponse(recipientBankAccount);
    }


}
