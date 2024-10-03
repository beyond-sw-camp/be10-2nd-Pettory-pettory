package com.pettory.pettory.board.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
@Schema(description = "게시글 상세 내용 DTO")
public class BoardPostDetailDTO {
    private int postNum;
    private String postTitle;
    private String postContent;
    private int postHits;
    private int postWriterNum;
    private LocalDateTime postInsertDatetime;

    // 명시적 생성자 추가
    public BoardPostDetailDTO(int postNum, String postTitle, String postContent, int postHits, int postWriterNum, LocalDateTime postInsertDatetime) {
        this.postNum = postNum;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postHits = postHits;
        this.postWriterNum = postWriterNum;
        this.postInsertDatetime = postInsertDatetime;
    }


}
