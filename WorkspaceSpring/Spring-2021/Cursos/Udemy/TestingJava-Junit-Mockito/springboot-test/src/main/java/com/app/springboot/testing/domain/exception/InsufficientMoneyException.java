package com.app.springboot.testing.domain.exception;

public class InsufficientMoneyException extends RuntimeException {

    public InsufficientMoneyException(String message) {
        super(message);
    }
}
