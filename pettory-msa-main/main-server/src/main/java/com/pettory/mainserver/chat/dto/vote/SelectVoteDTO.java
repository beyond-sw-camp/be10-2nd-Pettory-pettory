package com.pettory.mainserver.chat.dto.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SelectVoteDTO {
    private int voteUniqueNum;
    private int voteChatroomUniqueNum;
    private String voteTitle;
    private String voteContent;
    private LocalDateTime voteInsertTime;
    private LocalDateTime voteUpdateTime;
    private LocalDateTime voteDeleteTime;
    private String voteState;

    public SelectVoteDTO() {}
}
