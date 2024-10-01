package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WalkingGroupApplicationCreateRequest {

    private final int walkingGroupId;
    private final int userId;

}
