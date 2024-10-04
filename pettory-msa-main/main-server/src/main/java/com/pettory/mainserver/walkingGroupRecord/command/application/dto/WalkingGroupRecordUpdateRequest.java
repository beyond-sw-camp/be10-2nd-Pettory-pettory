package com.pettory.mainserver.walkingGroupRecord.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Schema(description = "산책모임기록수정")
public class WalkingGroupRecordUpdateRequest {

    @NotNull
    @Schema(description = "산책모임일시", example = "2024-10-02")
    private final LocalDate walkingGroupDate;
    @NotNull
    @Schema(description = "산책모임기록시간", example = "100")
    private final int walkingGroupRecordDuration;
    @NotBlank
    @Schema(description = "산책모임기록경로시작", example = "서울숲역")
    private final String walkingGroupRouteStart;
    @NotBlank
    @Schema(description = "산책모임기록경로끝", example = "뚝섬역")
    private final String walkingGroupRouteEnd;
    @NotNull
    @Schema(description = "산책모임기록상태", example = "COMPLETE")
    private final String walkingGroupRecordState;

}
