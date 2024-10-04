package com.pettory.userserver.exception;

public class BadJoinException extends RuntimeException {
    public BadJoinException(String message) {
        super(message);
    }
}
