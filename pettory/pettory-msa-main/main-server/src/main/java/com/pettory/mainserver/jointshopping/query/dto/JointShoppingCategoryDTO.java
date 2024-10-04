package com.pettory.mainserver.jointshopping.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "카테고리 DTO")
public class JointShoppingCategoryDTO {
    private Long jointShoppingCategoryNum;
    private String jointShoppingCategoryTitle;
}
