package com.pettory.pettory.jointshopping.query.dto;

import lombok.Getter;

@Getter
public class JointShoppingGroupDetailResponse {
    private JointShoppingGroupDTO group;

    public JointShoppingGroupDetailResponse(JointShoppingGroupDTO group) {
        this.group = group;
    }
}
