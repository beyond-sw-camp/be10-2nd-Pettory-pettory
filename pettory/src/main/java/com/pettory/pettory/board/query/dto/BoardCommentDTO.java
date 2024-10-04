package com.pettory.pettory.board.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 댓글 DTO")
public class BoardCommentDTO {
    private int postNum;
    private String commentContent;
    private LocalDateTime commentInsertDateTime;
    private int userId;
    private int recommentNum;
}
