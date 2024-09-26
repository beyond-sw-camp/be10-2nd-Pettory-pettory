package com.pettory.pettory.walkingGroupRecord.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WalkingGroupRecordCreateRequest {

    @NotNull
    private final LocalDate walkingGroupDate;
    @NotNull
    private final int walkingGroupRecordDuration;
    @NotBlank
    private final String walkingGroupRouteStart;
    @NotBlank
    private final String walkingGroupRouteEnd;
    private final int walkingGroupId;

}
