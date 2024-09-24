package com.pettory.pettory.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    /* Test 를 위한 testChatting */
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }

    /* 채팅방 생성 */
    @PostMapping("/insertChatRoom")
    public void insertChatRoom() {

    }

    /* 채팅방의 채팅 내용을 저장 */
    @PostMapping("/insertChatting")
    public void insertChatting() {

    }

    /* 채팅방의 채팅 내용을 수정 */
    @PostMapping("/modifyChatting")
    public void modifyChatting() {

    }

    /* 채팅방의 채팅 내용을 삭제 */
    @PostMapping("/deleteChatting")
    public void deleteChatting() {

    }

    /* 채팅방의 채팅 상태 변경 */
    @PostMapping("/chattingStateChange")
    public void chattingStateChange() {

    }
}
