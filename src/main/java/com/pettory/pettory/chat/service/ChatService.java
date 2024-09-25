package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
