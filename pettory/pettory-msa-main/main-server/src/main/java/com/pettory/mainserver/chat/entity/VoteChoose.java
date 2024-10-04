package com.pettory.mainserver.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="vote_choose_table")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class VoteChoose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteChoiceUniqueNum;
    private int voteUniqueNum;
    private String voteChoice;
    private String voteChoiceContent;
}
