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
public class InsertVoteDTO {
    private int voteUniqueNum;
    private int voteChatroomUniqueNum;
    private String voteTitle;
    private String voteContent;
    private LocalDateTime voteInsertTime;
    private String voteState;
}