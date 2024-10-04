package com.pettory.mainserver.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="vote_choose_result_table")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class VoteChooseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteChoiceResultUniqueNum;
    private int voteChoiceUniqueNum;
    private LocalDateTime voteChoiceResultInsertTime;
    private LocalDateTime voteChoiceResultUpdateTime;
    private LocalDateTime voteChoiceResultDeleteTime;
    private String voteChoiceResultState;
}
