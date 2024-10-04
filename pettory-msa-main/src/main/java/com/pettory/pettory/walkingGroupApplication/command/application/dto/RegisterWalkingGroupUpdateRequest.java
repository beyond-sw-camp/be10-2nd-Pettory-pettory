package com.pettory.pettory.walkingGroupApplication.command.application.dto;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroupState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "가입한 산책 모임 수정")
public class RegisterWalkingGroupUpdateRequest {

    @Schema(description = "가입한 산책 모임 상태", example = "ACTIVE")
    private String registerWalkingGroupState;

    public RegisterWalkingGroupUpdateRequest(String registerWalkingGroupState) {
        this.registerWalkingGroupState = registerWalkingGroupState;
    }
}
