package com.pettory.pettory.board.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequest {

    private final String postTitle;
    private final String postContent;
    private final int postWriterNum;
    private final int postCategoryNum;
}
