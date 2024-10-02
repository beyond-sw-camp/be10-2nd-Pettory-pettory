package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "산책모임상태수정")
public class WalkingGroupApplicationUpdateRequest {

    @NotNull
    @Schema(description = "산책모임승인상태", example = "REFUSAL")
    private  String walkingGroupApprovalState;

}
