package com.pettory.mainserver.exception;

public class AlreadySentInvitationException extends RuntimeException {
    public AlreadySentInvitationException(String message) {
        super(message);
    }
}
