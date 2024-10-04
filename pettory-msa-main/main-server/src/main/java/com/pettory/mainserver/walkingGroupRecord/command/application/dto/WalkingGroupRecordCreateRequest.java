package com.pettory.mainserver.walkingGroupRecord.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Schema(description = "산책모임기록생성")
public class WalkingGroupRecordCreateRequest {

    @NotNull
    @Schema(description = "산책모임기록날짜", example = "2024-10-02")
    private final LocalDate walkingGroupDate;
    @NotNull
    @Schema(description = "산책모임기록시간", example = "90")
    private final int walkingGroupRecordDuration;
    @NotBlank
    @Schema(description = "산책모임기록경로시작", example = "여의도한강공원")
    private final String walkingGroupRouteStart;
    @NotBlank
    @Schema(description = "산책모임기록경로끝", example = "반포한강공원")
    private final String walkingGroupRouteEnd;
    @Schema(description = "산책모임ID", example = "1")
    private final int walkingGroupId;

}
