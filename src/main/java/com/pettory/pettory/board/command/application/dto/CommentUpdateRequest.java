package com.pettory.pettory.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "댓글 수정 요청")
public class CommentUpdateRequest {

    private final String commentContent;

    @JsonCreator
    public CommentUpdateRequest(@JsonProperty("commentContent") String commentContent) {
        this.commentContent = commentContent;
    }
}