package com.pettory.pettory.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNum;
    private String commentContent;
    @Enumerated(EnumType.STRING)
    private CommentState commentState;
    private LocalDateTime commentInsertDatetime;
    private LocalDateTime commentUpdateDatetime;
    private LocalDateTime commentDeleteDatetime;
    private int postNum;
    private int recommentNum;
    private int userId;

    // CommentRequest를 통해 필요한 값들로 설정하는 생성자
    public Comment(String commentContent,int postNum, int recommentNum, int userId){
        this.commentContent = commentContent;
        this.commentState = CommentState.ACTIVE;
        this.commentInsertDatetime = LocalDateTime.now();
        this.commentUpdateDatetime = null;
        this.commentDeleteDatetime = null;
        this.postNum = postNum;
        this.recommentNum = recommentNum;
        this.userId = userId;
    }

    // 댓글 수정
    public void updateComment(String commentContent) {
        this.commentContent = commentContent;
        this.commentUpdateDatetime = LocalDateTime.now();
    }

    public void markAsDeleted() {
        this.commentState = CommentState.DELETE;
        this.commentDeleteDatetime = LocalDateTime.now();
    }
}
