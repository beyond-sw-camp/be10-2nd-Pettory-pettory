package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.chatroom.InsertChatRoomDTO;
import com.pettory.pettory.chat.dto.chatroom.DeleteChatRoomDTO;
import com.pettory.pettory.chat.dto.chatting.InsertChattingDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SelectChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import com.pettory.pettory.chat.entity.ChatRoom;
import com.pettory.pettory.chat.entity.Chatting;
import com.pettory.pettory.chat.mapper.ChattingMapper;
import com.pettory.pettory.chat.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ModelMapper chatModelMapper;
    private final ChattingMapper chattingMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final ChattingRepository chattingRepository;

    public ChatService(ModelMapper chatModelMapper,
                       ChattingMapper chattingMapper,
                       ChatRoomRepository chatRoomRepository,
                       ChattingRepository chattingRepository) {
        this.chatModelMapper = chatModelMapper;
        this.chattingMapper = chattingMapper;
        this.chatRoomRepository = chatRoomRepository;
        this.chattingRepository = chattingRepository;
    }

    /* 1. 채팅방 추가 */
    @Transactional
    public ChatRoom registerChatRoom(InsertChatRoomDTO insertChatRoomDTO) {
        return chatRoomRepository.save(chatModelMapper.map(insertChatRoomDTO, ChatRoom.class));
    }

    /* 2. 채팅방 상태 수정 */
    @Transactional
    public void deleteChatRoom(DeleteChatRoomDTO deleteChatRoomDTO) {
        ChatRoom chatRoom = chatRoomRepository.findById(deleteChatRoomDTO.getChatroomUniqueNum()).orElseThrow(IllegalArgumentException::new);
        chatRoom.modifyChatRoomState(deleteChatRoomDTO.getChatroomState());
        chatRoom.modifyChatRoomUpdateTime(deleteChatRoomDTO.getChatroomUpdateTime());
        chatRoom.modifyChatRoomDeleteTime(deleteChatRoomDTO.getChatroomDeleteTime());
    }

    /* 3. 채팅 내용 DB 저장 */
    @Transactional
    public void registerChatting(InsertChattingDTO insertChattingDTO) {
        chattingRepository.save(chatModelMapper.map(insertChattingDTO, Chatting.class));
    }

    /* 4. 채팅 내용 수정 */
    @Transactional
    public Chatting modifyChatting(ModifyChattingDTO modifyChattingDTO) {
        Chatting chatting = chattingRepository.findById(modifyChattingDTO.getChattingUniqueNum()).orElseThrow(IllegalArgumentException::new);
        /* 채팅 수정 메소드 */
        chatting.modifyChatting(modifyChattingDTO.getChattingContent(),
                                modifyChattingDTO.getChattingUpdateTime(),
                                modifyChattingDTO.getChattingState());
        return chatting;
    }

    /* 5-1. 채팅 얇은 삭제 */
    @Transactional
    public void softDeleteChatting(SoftDeleteChattingDTO softDeleteChattingDTO) {
        Chatting chatting = chattingRepository.findById(softDeleteChattingDTO.getChattingUniqueNum()).orElseThrow(IllegalArgumentException::new);
        /* 채팅 얇은 삭제 기능 메소드 : 상태 변경, deleteTime 삽입 */
        chatting.softDeleteChatting(softDeleteChattingDTO.getChattingState(),
                                    softDeleteChattingDTO.getChattingDeleteTime());
    }

    /* 5-2. 채팅 리얼 삭제 */
    @Transactional
    public void hardDeleteChatting(Integer chattingUniqueNum) {
        chattingRepository.deleteById(chattingUniqueNum);
    }

    /* 6. 채팅 조회 */
    public List<SelectChattingDTO> selectChatRoomChatting(Integer chatRoomUniqueNum) {
        return chattingMapper.selectChatRoomChatting(chatRoomUniqueNum);
    }
}
