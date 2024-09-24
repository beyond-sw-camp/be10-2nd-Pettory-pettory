package com.pettory.pettory.walkinggroup.command.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WalkingGroupUpdateRequest {

    @NotBlank
    private final String walkingGroupName;
    @NotBlank
    private final String walkingGroupInfo;
    @Min(value = 2)
    private final int walkingGroupMaximumCount;
    @NotNull
    private final String walkingGroupState;
}
