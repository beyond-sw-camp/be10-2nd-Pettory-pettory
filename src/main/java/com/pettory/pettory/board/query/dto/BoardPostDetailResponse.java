package com.pettory.pettory.board.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "게시글 상세 내용 요청")
public class BoardPostDetailResponse {
    private List<BoardPostDetailDTO> postDetail;
//    private List<String> fileLinks;

//    public BoardPostDetailResponse(List<BoardPostDetailDTO> postDetail /*List<String> fileLinks*/) {
//        this.postDetail = postDetail;
////        this.fileLinks = fileLinks;
//    }
}
