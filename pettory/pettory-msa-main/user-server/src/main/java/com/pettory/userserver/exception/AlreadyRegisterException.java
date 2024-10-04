package com.pettory.userserver.exception;

public class AlreadyRegisterException extends RuntimeException {
    public AlreadyRegisterException(String message) {
        super(message);
    }
}
