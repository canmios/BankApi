package com.ontop.bank.service.exception;

public class InvalidBankAccountException extends RuntimeException {

    public InvalidBankAccountException(String message){
        super(message);
    }
}
