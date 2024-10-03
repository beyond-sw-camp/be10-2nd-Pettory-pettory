package com.pettory.pettory.walkinggroup.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "산책모임 상세 정보")
// walkingGroupDTO에서 원하는 정보만을 뽑고 새로운 정보를 추가하기 위해
public class WalkingGroupDetailResponse {
    private WalkingGroupDTO walkingGroupsById;

    public WalkingGroupDetailResponse(WalkingGroupDTO walkingGroupsById) {
        this.walkingGroupsById = walkingGroupsById;
    }
}
