package com.pettory.pettory.jointshopping.query.dto;

import lombok.Getter;

@Getter
public class GroupDetailResponse {
    private GroupDTO group;

    public GroupDetailResponse(GroupDTO group) {
        this.group = group;
    }
}
