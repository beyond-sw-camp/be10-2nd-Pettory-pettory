package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.enums.ChatRoomStateEnum;
import com.pettory.pettory.chat.dto.ChatRoomDTO;
import com.pettory.pettory.chat.enums.ChatRoomTypeEnum;
import com.pettory.pettory.chat.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private ChatService chatService;

    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    /* Test 를 위한 Sample testChatting */
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }

    /* [Test] 채팅방 생성 후 그 채팅방으로 이동함. */
//    @GetMapping("/testChat")
//    public String testChat(@ModelAttribute ChatRoomDTO chatRoomDTO, Model model) {
//
//
//        return "testChat";
//    }

    /* 채팅방 생성 */
    @PostMapping("/chatroom")
    public String insertChatRoom(@ModelAttribute ChatRoomDTO chatRoomDTO, Model model) {
        /* 산책 모임방, 공동 구매 모임방 생성시 chatRoomDTO setting
        - 프론트 단에서 dto 생성해서 넘겨 받을 예정, 임시작업 */
        LocalDateTime localDateTime = LocalDateTime.now();
        chatRoomDTO.setChatRoomInsertTime(localDateTime);
        chatRoomDTO.setChatRoomState(String.valueOf(ChatRoomStateEnum.ACTIVE));
        chatRoomDTO.setChatRoomType(String.valueOf(ChatRoomTypeEnum.WALKING));
        chatRoomDTO.setChatRoomTypeNum(1);

        /* 채팅방 DB 등록*/
        chatService.registerChatRoom(chatRoomDTO);

        /* 등록된 채팅방에 첫 메시지 등록 */
        //model.addAttribute(chatRoomDTO);

        return "testChatting";
    }

    /* 채팅방 상태 수정
    * 이슈 : Update 와 delete 기능이 같음. delete 기능은 구현하지 않을 예정
    * */
    @PutMapping("/chatroom")
    public String modifyChatRoomState(@ModelAttribute ChatRoomDTO chatRoomDTO) {
        /* 산책 모임방, 공동 구매 모임방 생성시 chatRoomDTO setting
        - 프론트 단에서 dto 생성해서 넘겨 받을 예정, 임시작업, 프론트에서 chatRoomDTO 에서 DELETE 요청 가정 */
        LocalDateTime localDateTime = LocalDateTime.now();
        chatRoomDTO.setChatRoomUpdateTime(localDateTime);
        chatRoomDTO.setChatRoomDeleteTime(localDateTime);
        chatRoomDTO.setChatRoomState(String.valueOf(ChatRoomStateEnum.DELETE));
        chatRoomDTO.setChatRoomUniqueNum(2);

        chatService.modifyChatRoom(chatRoomDTO);

        return "testChatting";
    }

    /* 채팅방의 채팅 내용을 저장 */
    @PostMapping("/chatting")
    public void insertChatting() {

    }

    /* 채팅방의 채팅 내용을 수정 */
    @PostMapping("/")
    public void modifyChatting() {

    }

    /* 채팅방의 채팅 내용을 삭제 */
    @PostMapping("/Chatting")
    public void deleteChatting() {

    }

    /* 채팅방의 채팅 상태 변경 */
    @PostMapping("/chattingStateChange")
    public void chattingStateChange() {

    }
}
