package com.pettory.userserver.exception;

public class AlreadySentInvitationException extends RuntimeException {
    public AlreadySentInvitationException(String message) {
        super(message);
    }
}
