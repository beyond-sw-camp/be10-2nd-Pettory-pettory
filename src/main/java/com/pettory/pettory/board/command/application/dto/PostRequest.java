package com.pettory.pettory.board.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "게시글 생성 요청")
public class PostRequest {

    private final String postTitle;
    private final String postContent;
    private final int postWriterNum;
    private final int postCategoryNum;
}
