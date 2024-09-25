package com.pettory.pettory.chat.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChattingDTO {
    private int chattingChatRoomUniqueNum;
    private String chattingContent;
    private LocalDateTime chattingInsertTime;
    private LocalDateTime chattingUpdateTime;
    private LocalDateTime chattingDeleteTime;
    private String chattingState;
    private int userId;
}
