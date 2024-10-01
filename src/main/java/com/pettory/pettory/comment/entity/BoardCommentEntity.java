package com.pettory.pettory.comment.entity;

import com.pettory.pettory.board.entity.BoardEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_num")
    private int commentNum;

    @Lob
    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_state")
    private String commentState;

    @Column(name = "post_insert_datetime")
    private LocalDateTime postInsertDatetime;

    @Column(name = "post_update_datetime")
    private LocalDateTime postUpdateDatetime;

    @Column(name = "post_delete_datetime")
    private LocalDateTime postDeleteDatetime;

    @ManyToOne
    @JoinColumn(name = "post_num", nullable = false)
    private BoardEntity post;

    @ManyToOne
    @JoinColumn(name = "recomment_num")
    private BoardCommentEntity recommentNum;
}
