package com.pettory.pettory.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_num") // 명시적으로 컬럼 이름 지정
    private int commentNum;

    @Column(name = "comment_content") // 명시적으로 컬럼 이름 지정
    private String commentContent;

    @Column(name = "comment_state")
    private String commentState;

    @Column(name = "post_insert_datetime")
    private String postInsertDatetime;

    @Column(name = "post_update_datetime")
    private String postUpdateDatetime;

    @Column(name = "post_delete_datetime")
    private String postDeleteDatetime;

    @Column(name = "post_num")
    private int postNum;

    @Column(name = "recomment_num")
    private int recommentNum;

}
