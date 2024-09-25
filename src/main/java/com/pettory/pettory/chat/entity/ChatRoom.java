package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/* ChatRoomTable 에 대한 Entity 생성 */
@Entity
@Getter
@Table(name="chatroom_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chatroomUniqueNum;
    private LocalDateTime chatroomInsertTime;
    private LocalDateTime chatroomUpdateTime;
    private LocalDateTime chatroomDeleteTime;
    private String chatroomState;
    private String chatroomType;
    private int chatroomTypeNum;

    public void modifyChatRoomState(String chatRoomState) {
        this.chatroomState = chatRoomState;
    }

    public void modifyChatRoomUpdateTime(LocalDateTime chatRoomUpdateTime) {
        this.chatroomUpdateTime = chatRoomUpdateTime;
    }

    public void modifyChatRoomDeleteTime(LocalDateTime chatRoomDeleteTime) {
        this.chatroomDeleteTime = chatRoomDeleteTime;
    }
}
