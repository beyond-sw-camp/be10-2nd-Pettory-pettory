package com.pettory.mainserver.board.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentUpdateRequest {

    private final String commentContent;

}
