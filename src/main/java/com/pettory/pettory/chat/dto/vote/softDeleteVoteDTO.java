package com.pettory.pettory.chat.dto.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class softDeleteVoteDTO {
    private int voteUniqueNum;
    private int voteChatroomUniqueNum;
    private LocalDateTime voteDeleteTime;
    private String voteState;
}