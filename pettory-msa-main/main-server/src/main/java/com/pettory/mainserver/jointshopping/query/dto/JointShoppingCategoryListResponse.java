package com.pettory.mainserver.jointshopping.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "카테고리 응답")
public class JointShoppingCategoryListResponse {
    private List<JointShoppingCategoryDTO> categoryList;
    private long totalItems;            // 총 아이템 수
}
