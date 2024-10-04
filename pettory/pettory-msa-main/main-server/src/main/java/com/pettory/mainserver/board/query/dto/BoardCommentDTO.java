package com.pettory.mainserver.board.query.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class BoardCommentDTO {
    private int postNum;
    private String commentContent;
    private String commentInsertDateTime;
    private int userId;
    private int recommentNum;
}
