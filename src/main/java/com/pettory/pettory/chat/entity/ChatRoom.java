package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* ChatRoomTable 에 대한 Entity 생성 */
@Entity
@Table(name="chatroom_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int chatroomUniqueNum;
    private int chatroomInsertTime;
    private int chatroomUpdateTime;
    private int chatroomDeleteTime;
    private int chatroomState;
    private int chatroomType;
    private int chatroomTypeNum;
}
