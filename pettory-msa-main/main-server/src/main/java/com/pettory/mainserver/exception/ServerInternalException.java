package com.pettory.mainserver.exception;

public class ServerInternalException extends RuntimeException{
    public ServerInternalException(String message) {
        super(message);
    }
}
