package com.pettory.mainserver.board.query.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardPostDTO {
    private int postNum;
    private String postTitle;
    private int postHits;
    private String postInsertDatetime;
}
