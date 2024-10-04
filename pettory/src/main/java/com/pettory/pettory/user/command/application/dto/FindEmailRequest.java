package com.pettory.pettory.user.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindEmailRequest {
    private String userNickname;
}
