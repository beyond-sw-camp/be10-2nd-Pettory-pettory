package com.pettory.pettory.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "카테고리 생성 요청")
public class CategoryRequest {

    private final String categoryTitle;

    @JsonCreator
    public CategoryRequest(@JsonProperty("categoryTitle") String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
