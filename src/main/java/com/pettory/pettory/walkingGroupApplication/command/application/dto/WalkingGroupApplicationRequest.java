package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "산책모임신청승인변경")
public class WalkingGroupApplicationRequest {

    @Schema(description = "산책모임승인상태", example = "ACCEPT")
    private String walkingGroupApprovalState;

}
