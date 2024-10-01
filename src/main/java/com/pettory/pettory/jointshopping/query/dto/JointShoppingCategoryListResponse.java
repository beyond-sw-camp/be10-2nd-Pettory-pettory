package com.pettory.pettory.jointshopping.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class JointShoppingCategoryListResponse {
    private List<JointShoppingCategoryDTO> categoryList;
    private long totalItems;            // 총 아이템 수
}
