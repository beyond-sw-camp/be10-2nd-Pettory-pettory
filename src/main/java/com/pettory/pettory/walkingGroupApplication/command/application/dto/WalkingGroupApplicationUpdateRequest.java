package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
//@RequiredArgsConstructor
public class WalkingGroupApplicationUpdateRequest {

    @NotNull
    private  String walkingGroupApprovalState;
}
