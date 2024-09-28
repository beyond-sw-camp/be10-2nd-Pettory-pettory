package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.chatting.InsertChattingDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import com.pettory.pettory.chat.entity.ChatRoom;
import com.pettory.pettory.chat.dto.ChatRoomDTO;
import com.pettory.pettory.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    /* Test 를 위한 Sample testChatting */
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }

    /* 1. 채팅방 생성 */
    @PostMapping("/chatroom")
    public ResponseEntity<?> registerChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        chatRoomDTO.setChatRoomInsertTime(localDateTime);

        /* 채팅방 DB 등록*/
        ChatRoom chatroom = chatService.registerChatRoom(chatRoomDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatroom/" + chatroom.getChatroomUniqueNum())
        ).build();
    }

    /* 2. 채팅방 상태 수정 */
    @PutMapping("/chatroom/{chatRoomUniqueNum}")
    public ResponseEntity<?> modifyChatRoomState(@PathVariable Integer chatRoomUniqueNum,
                                                 @RequestBody ChatRoomDTO chatRoomDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        chatRoomDTO.setChatRoomUniqueNum(chatRoomUniqueNum);
        chatRoomDTO.setChatRoomUpdateTime(localDateTime);

        /* 채팅방 상태 DB 수정 */
        chatService.modifyChatRoom(chatRoomDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatroom-state/" + chatRoomDTO.getChatRoomUniqueNum())
        ).build();
    }

    /* 3. 채팅방의 채팅 내용을 저장 */
    @PostMapping("/chatting")
    public ResponseEntity<?> registerChatting(@RequestBody InsertChattingDTO insertChattingDTO) {

        chatService.registerChatting(insertChattingDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatting/" + insertChattingDTO.getChattingUniqueNum())
        ).build();
    }

    /* 4. 채팅방의 채팅 내용을 수정 */
    @PutMapping("/chatting/{chattingUniqueNum}")
    public ResponseEntity<?> modifyChatting(@PathVariable Integer chattingUniqueNum,
                                            @RequestBody ModifyChattingDTO modifyChattingDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();

        modifyChattingDTO.setChattingUniqueNum(chattingUniqueNum);
        modifyChattingDTO.setChattingUpdateTime(localDateTime);

        chatService.modifyChatting(modifyChattingDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatting/" + modifyChattingDTO.getChattingUniqueNum())
        ).build();
    }

    /* 채팅방의 채팅 내용을 soft Delete */
    @DeleteMapping("/chatting-soft/{chattingUniqueNum}")
    public ResponseEntity<?> softDeleteChatting(@PathVariable Integer chattingUniqueNum,
                                                SoftDeleteChattingDTO softDeleteChattingDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        softDeleteChattingDTO.setChattingUniqueNum(chattingUniqueNum);
        softDeleteChattingDTO.setChattingDeleteTime(localDateTime);

        chatService.softDeleteChatting(softDeleteChattingDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatting-soft/" + softDeleteChattingDTO.getChattingUniqueNum())
        ).build();
    }

    /* 채팅방의 채팅 내용을 Hard Delete */
    @DeleteMapping("/chatting-hard/{chattingUniqueNum}")
    public ResponseEntity<?> hardDeleteChatting(@PathVariable Integer chattingUniqueNum) {
        chatService.hardDeleteChatting(chattingUniqueNum);

        return ResponseEntity.noContent().build();
    }
}
