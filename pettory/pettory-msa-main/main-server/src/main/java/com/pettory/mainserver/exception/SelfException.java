package com.pettory.mainserver.exception;

public class SelfException extends RuntimeException {
    public SelfException(String message) {
        super(message);
    }
}
