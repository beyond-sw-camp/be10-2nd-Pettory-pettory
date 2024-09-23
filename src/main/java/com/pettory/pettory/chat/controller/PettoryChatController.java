package com.pettory.pettory.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PettoryChatController {

    /* Test 를 위한 testChatting */
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }
}
