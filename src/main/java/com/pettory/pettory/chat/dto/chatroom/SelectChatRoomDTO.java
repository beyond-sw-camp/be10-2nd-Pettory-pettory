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
public class SelectChatRoomDTO {
    private int chatRoomUniqueNum;
    private LocalDateTime chatRoomInsertTime;
    private LocalDateTime chatRoomUpdateTime;
    private LocalDateTime chatRoomDeleteTime;
    private String chatRoomState;
    private String chatRoomType;
    private int chatRoomTypeNum;
}
