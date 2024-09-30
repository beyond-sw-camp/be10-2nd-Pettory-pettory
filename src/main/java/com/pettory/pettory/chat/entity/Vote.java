package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="vote_table")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteUniqueNum;
    private int voteChatRoomUniqueNum;
    private String voteTitle;
    private String voteContent;
    private LocalDateTime voteInsertTime;
    private LocalDateTime voteUpdateTime;
    private LocalDateTime voteDeleteTime;
    private String voteState;

    /* 투표 제목, 내용 동시 수정 */
    public void modifyVoteTitleContent(String voteTitle, String voteContent, LocalDateTime voteUpdateTime, String voteState) {
        this.voteTitle = voteTitle;
        this.voteContent = voteContent;
        this.voteUpdateTime = voteUpdateTime;
        this.voteState = voteState;
    }

    /* 투표 얇은 삭제 */
    public void softDeleteVote(LocalDateTime voteDeleteTime, String voteState) {
        this.voteDeleteTime = voteDeleteTime;
        this.voteState = voteState;
    }
}
