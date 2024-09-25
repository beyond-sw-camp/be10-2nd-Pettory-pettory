package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.ChatRoomDTO;
import com.pettory.pettory.chat.dto.ChattingDTO;
import com.pettory.pettory.chat.entity.ChatRoom;
import com.pettory.pettory.chat.entity.Chatting;
import com.pettory.pettory.chat.enums.ChatRoomStateEnum;
import com.pettory.pettory.chat.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ChatService {
    private final ModelMapper chatModelMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final ChattingFileRepository chattingFileRepository;
    private final ChattingRepository chattingRepository;
    private final VoteChooseRepository voteChooseRepository;
    private final VoteChooseResultRepository voteChooseResultRepository;
    private final VoteRepository voteRepository;

    public ChatService(ChatRoomRepository chatRoomRepository,
                       ChattingFileRepository chattingFileRepository,
                       ChattingRepository chattingRepository,
                       VoteChooseRepository voteChooseRepository,
                       VoteChooseResultRepository voteChooseResultRepository,
                       VoteRepository voteRepository,
                       ModelMapper chatModelMapper) {
        this.chatModelMapper = chatModelMapper;
        this.chatRoomRepository = chatRoomRepository;
        this.chattingFileRepository = chattingFileRepository;
        this.chattingRepository = chattingRepository;
        this.voteChooseRepository = voteChooseRepository;
        this.voteChooseResultRepository = voteChooseResultRepository;
        this.voteRepository = voteRepository;
    }

    /* 1. 채팅방 추가 */
    @Transactional
    public void registerChatRoom(ChatRoomDTO chatRoomDTO) {
        chatRoomRepository.save(chatModelMapper.map(chatRoomDTO, ChatRoom.class));
    }

    /* 2. 채팅방 상태 수정 */
    @Transactional
    public void modifyChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomDTO.getChatRoomUniqueNum()).orElseThrow(IllegalArgumentException::new);
        chatRoom.modifyChatRoomState(chatRoomDTO.getChatRoomState());
        chatRoom.modifyChatRoomUpdateTime(chatRoomDTO.getChatRoomUpdateTime());
        chatRoom.modifyChatRoomDeleteTime(chatRoomDTO.getChatRoomDeleteTime());
    }

    public void registerChatting(ChattingDTO chattingDTO) {
        chattingRepository.save(chatModelMapper.map(chattingDTO, Chatting.class));
    }
}
