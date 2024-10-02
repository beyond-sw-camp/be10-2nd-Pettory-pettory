package com.pettory.pettory.walkinggroup.query.dto;

import lombok.Getter;

import java.util.List;

@Getter
// walkingGroupDTO에서 원하는 정보만을 뽑고 새로운 정보를 추가하기 위해
public class WalkingGroupDetailResponse {
    private List<WalkingGroupDTO> walkingGroupsByName;

    public WalkingGroupDetailResponse(List<WalkingGroupDTO> walkingGroupsByName) {
        this.walkingGroupsByName = walkingGroupsByName;
    }
}
