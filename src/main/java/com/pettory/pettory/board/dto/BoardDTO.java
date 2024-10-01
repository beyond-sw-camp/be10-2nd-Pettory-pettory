package com.pettory.pettory.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int postNum;
    private String postContent;
    private String postTitle;
    private int postHits;
    private String postDeleteDatetime;
    private PostState postState;
    private String postInsertDatetime;
    private String postUpdateDatetime;
    private int postWriterNum;
    private int postCategoryNum;
}
