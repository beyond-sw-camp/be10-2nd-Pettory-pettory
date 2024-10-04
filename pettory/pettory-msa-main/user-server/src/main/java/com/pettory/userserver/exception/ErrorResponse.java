package com.pettory.userserver.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private int statusCode;
    private String message;
}
