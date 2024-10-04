package com.pettory.mainserver.chat.dto.votechoose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class VoteChooseDTO {
    private int voteChoiceUniqueNum;
    private int voteUniqueNum;
    private String voteChoice;
    private String voteChoiceContent;

    public VoteChooseDTO() {}
}
