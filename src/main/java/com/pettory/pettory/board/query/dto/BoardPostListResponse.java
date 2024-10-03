package com.pettory.pettory.board.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "게시글 목록 요청")
public class BoardPostListResponse {
    private List<BoardPostDTO> postList;
    private int totalPosts;
}
