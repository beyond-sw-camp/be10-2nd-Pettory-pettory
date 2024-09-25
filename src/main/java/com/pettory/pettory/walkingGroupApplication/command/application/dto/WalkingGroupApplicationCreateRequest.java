package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApprovalState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class WalkingGroupApplicationCreateRequest {

    private final int walkingGroupId;
    private final int userId;

}
