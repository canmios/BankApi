package com.bank.service.exception;

public class InvalidPaymentException extends RuntimeException{

    public InvalidPaymentException(String message) {
        super(message);
    }
}
