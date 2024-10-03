package com.pettory.pettory.walkingGroupApplication.query.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WalkingGroupApplicationDetailResponse {
    private List<WalkingGroupApplicationDTO> walkingGroupApplicationById;

    public WalkingGroupApplicationDetailResponse(List<WalkingGroupApplicationDTO> walkingGroupApplicationById) {
        this.walkingGroupApplicationById = walkingGroupApplicationById;
    }
}
