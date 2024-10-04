package com.pettory.pettory.jointshopping.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "공동구매모임 상세 응답")
public class JointShoppingGroupDetailResponse {
    private JointShoppingGroupDTO group;

    public JointShoppingGroupDetailResponse(JointShoppingGroupDTO group) {
        this.group = group;
    }
}
