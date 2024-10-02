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
public class InsertChattingDTO {
    private int chattingUniqueNum;
    private int chattingChatroomUniqueNum;
    private String chattingContent;
    private LocalDateTime chattingInsertTime;
    private String chattingState;
    private int userId;
}
