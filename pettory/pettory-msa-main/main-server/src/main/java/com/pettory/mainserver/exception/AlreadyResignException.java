package com.pettory.mainserver.exception;

public class AlreadyResignException extends RuntimeException {
    public AlreadyResignException(String message) {
        super(message);
    }
}
