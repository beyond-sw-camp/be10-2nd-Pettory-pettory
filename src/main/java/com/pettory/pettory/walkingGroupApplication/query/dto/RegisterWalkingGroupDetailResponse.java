package com.pettory.pettory.walkingGroupApplication.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "가입한 산책 모임 상세 정보")
public class RegisterWalkingGroupDetailResponse {
    private List<RegisterWalkingGroupDTO> registerWalkingGroup;

    public RegisterWalkingGroupDetailResponse(List<RegisterWalkingGroupDTO> registerWalkingGroup) {
        this.registerWalkingGroup = registerWalkingGroup;
    }
}
