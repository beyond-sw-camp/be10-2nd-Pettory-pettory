package com.pettory.mainserver.walkinggroup.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "산책모임수정")
public class WalkingGroupUpdateRequest {

    @Schema(description = "산책모임이름", example = "멍멍이와 같이 걸어요")
    @NotBlank
    private final String walkingGroupName;
    @Schema(description = "산책모임정보", example = "혼자 산책하기 싫은 분들 함께 산책해요")
    @NotBlank
    private final String walkingGroupInfo;
    @Min(value = 2)
    @Schema(description = "산책모임가입최대인원")
    private final int walkingGroupMaximumCount;
    @Schema(description = "산책모임상태", example = "APPLICATION")
    @NotNull
    private final String walkingGroupState;
}
