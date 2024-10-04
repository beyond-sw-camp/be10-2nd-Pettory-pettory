package com.pettory.mainserver.board.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardPostListResponse {
    private List<BoardPostDTO> postList;
    private int totalPosts;
}
