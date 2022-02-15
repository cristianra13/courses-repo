package com.app.testing.exception;

public class InsufficientMoneyException extends RuntimeException {

    public InsufficientMoneyException(String message) {
        super(message);
    }
}
