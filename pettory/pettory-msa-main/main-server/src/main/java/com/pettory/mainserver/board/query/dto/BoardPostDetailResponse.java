package com.pettory.mainserver.board.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardPostDetailResponse {
    private List<BoardPostDetailDTO> postDetail;
    private List<String> fileLinks;

    public BoardPostDetailResponse(List<BoardPostDetailDTO> postDetail, List<String> fileLinks) {
        this.postDetail = postDetail;
        this.fileLinks = fileLinks;
    }
}
