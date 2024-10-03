package com.pettory.pettory.board.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "게시글 생성 요청")
public class CommentRequest {

    private final String commentContent;
    private final int postNum;
    private final int recommentNum;
    private final int userId;
}
