package com.pettory.pettory.chat.dto.chatting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SoftDeleteChattingDTO {
    private int chattingUniqueNum;
    private int chattingChatroomUniqueNum;
    private LocalDateTime chattingDeleteTime;
    private String chattingState;

    public SoftDeleteChattingDTO() {}
}
