package com.pettory.userserver.exception;

public class AlreadyResignException extends RuntimeException {
    public AlreadyResignException(String message) {
        super(message);
    }
}
