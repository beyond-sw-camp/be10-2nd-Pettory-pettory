package com.pettory.pettory.chat.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class InsertChatRoomDTO {
    private int chatroomUniqueNum;
    private LocalDateTime chatroomInsertTime;
    private String chatroomState;
    private String chatroomType;
    private int chatroomTypeNum;
}
