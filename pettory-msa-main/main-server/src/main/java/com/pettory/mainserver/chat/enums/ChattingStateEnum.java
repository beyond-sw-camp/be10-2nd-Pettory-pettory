package com.pettory.mainserver.chat.enums;

public enum ChattingStateEnum {
    ACTIVE, // 채팅 활성화
    MODIFY, // 채팅 수정됨
    DELETE, // 채팅 삭제됨
    BLIND,  // 채팅 가려짐
    NOTICE  // 공지로 전환됨
}
