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
}
