package com.pettory.mainserver.board.query.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardCategoryDTO {
    private int categoryNum;
    private String categoryTitle;
}
