package com.pettory.pettory.board.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 카테고리 DTO")
public class BoardCategoryDTO {
    private int categoryNum;
    private String categoryTitle;
}
