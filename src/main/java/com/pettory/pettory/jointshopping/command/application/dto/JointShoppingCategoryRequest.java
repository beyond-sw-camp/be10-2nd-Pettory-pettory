package com.pettory.pettory.jointshopping.command.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
@Schema(description = "카테고리 요청")
public class JointShoppingCategoryRequest {

    @NotBlank
    private final String jointShoppingCategoryTitle;

    // JSON 파싱 과정에서 필드가 하나일 경우 이런식으로 생성자 선언 필요
    @ConstructorProperties(value = {"jointShoppingCategoryTitle"})
    public JointShoppingCategoryRequest(String jointShoppingCategoryTitle){
        this.jointShoppingCategoryTitle = jointShoppingCategoryTitle;
    }
}
