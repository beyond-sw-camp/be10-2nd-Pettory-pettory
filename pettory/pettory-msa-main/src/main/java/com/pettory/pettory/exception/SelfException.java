package com.pettory.pettory.exception;

public class SelfException extends RuntimeException {
    public SelfException(String message) {
        super(message);
    }
}
