package com.pettory.mainserver.walkinggroup.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "산책모임정보")
public class WalkingGroupCreateRequest {

    @NotBlank
    @Schema(description = "산책모임이름", example = "강아지와함께 산책")
    private final String walkingGroupName;
    @NotBlank
    @Schema(description = "산책모임정보", example = "아침에 강아지와 함께 산책을 합니다.")
    private final String walkingGroupInfo;
    @Min(value = 2)
    @Schema(description = "산책모임가입최대인원")
    private final int walkingGroupMaximumCount;
    @Schema(description = "산책모임방장")
    private final int walkingGroupOwner;
}
