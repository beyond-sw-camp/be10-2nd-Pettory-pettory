package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/* 채팅 Entity 생성 */
@Entity
@Getter
@Table(name="chatting_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chattingUniqueNum;
    private int chattingChatroomUniqueNum;
    private int chattingContent;
    private int chattingInsertTime;
    private int chattingUpdateTime;
    private int chattingDeleteTime;
    private int chattingState;
    private int userId;
}
