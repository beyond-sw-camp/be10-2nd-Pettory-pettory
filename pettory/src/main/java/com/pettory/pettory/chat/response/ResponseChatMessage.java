package com.pettory.pettory.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ResponseChatMessage {
    private int httpStatus;
    private String message;
    private Map<String, Object> results;
}
