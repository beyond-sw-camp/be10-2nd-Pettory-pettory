package com.pettory.pettory.board.entity;

import com.pettory.pettory.board.dto.PostState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postNum;
    @Setter
    private String postContent;
    @Setter
    private String postTitle;
    @Setter
    private int postHits;
    private String postDeleteDatetime;
    @Enumerated(EnumType.STRING)
    private PostState postState = PostState.ACTIVE;
    private String postInsertDatetime;
    private String postUpdateDatetime;
    private int postWriterNum;
    private int postCategoryNum;


}
