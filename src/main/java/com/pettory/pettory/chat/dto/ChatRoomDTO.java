package com.pettory.pettory.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/* 채팅방 DTO */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatRoomDTO {
    private int chatRoomUniqueNum;
    private LocalDateTime chatRoomInsertTime;
    private LocalDateTime chatRoomUpdateTime;
    private LocalDateTime chatRoomDeleteTime;
    private String chatRoomState;
    private String chatRoomType;
    private int chatRoomTypeNum;
}
