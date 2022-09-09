package com.sg.kata.exceptions;

public class KataBankingException extends RuntimeException {

    public KataBankingException() { }

    public KataBankingException(String message) {
        super(message);
    }

    public KataBankingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
