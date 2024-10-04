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
@Schema(description = "게시글 DTO")
public class BoardPostDTO {
    private int postNum;
    private String postTitle;
    private int postHits;
    private LocalDateTime postInsertDatetime;
}
