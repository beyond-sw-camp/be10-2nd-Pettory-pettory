package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
//@RequiredArgsConstructor
public class WalkingGroupApplicationUpdateRequest {

    @NotNull
    private  String walkingGroupApprovalState;

}
