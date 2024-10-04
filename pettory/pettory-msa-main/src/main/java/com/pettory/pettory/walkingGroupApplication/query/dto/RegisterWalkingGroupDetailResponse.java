package com.pettory.pettory.walkingGroupApplication.query.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RegisterWalkingGroupDetailResponse {
    private List<RegisterWalkingGroupDTO> registerWalkingGroup;

    public RegisterWalkingGroupDetailResponse(List<RegisterWalkingGroupDTO> registerWalkingGroup) {
        this.registerWalkingGroup = registerWalkingGroup;
    }
}
