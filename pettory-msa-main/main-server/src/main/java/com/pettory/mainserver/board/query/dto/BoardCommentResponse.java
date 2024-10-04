package com.pettory.mainserver.board.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardCommentResponse {
    private List<BoardCommentDTO> Comments;
    private int totalComments;

}
