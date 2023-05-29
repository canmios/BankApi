package com.bank.service.exception;

public class InvalidBankAccountException extends RuntimeException {

    public InvalidBankAccountException(String message){
        super(message);
    }
}
