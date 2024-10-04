package com.pettory.pettory.board.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequest {

    private final String commentContent;
    private final int postNum;
    private final int recommentNum;
    private final int userId;
}
