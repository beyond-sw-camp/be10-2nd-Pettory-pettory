package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.ChatRoomDTO;
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
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final SqlSessionTemplate sqlSession;
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
                       ModelMapper chatModelMapper,
                       SqlSessionTemplate sqlSession) {
        this.chatModelMapper = chatModelMapper;
        this.chatRoomRepository = chatRoomRepository;
        this.chattingFileRepository = chattingFileRepository;
        this.chattingRepository = chattingRepository;
        this.voteChooseRepository = voteChooseRepository;
        this.voteChooseResultRepository = voteChooseResultRepository;
        this.voteRepository = voteRepository;
        this.sqlSession = sqlSession;
    }

    /* 1. 채팅방 추가 */
    @Transactional
    public ChatRoom registerChatRoom(ChatRoomDTO chatRoomDTO) {
        return chatRoomRepository.save(chatModelMapper.map(chatRoomDTO, ChatRoom.class));
    }

    /* 2. 채팅방 상태 수정 */
    @Transactional
    public void modifyChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomDTO.getChatRoomUniqueNum()).orElseThrow(IllegalArgumentException::new);
        chatRoom.modifyChatRoomState(chatRoomDTO.getChatRoomState());
        chatRoom.modifyChatRoomUpdateTime(chatRoomDTO.getChatRoomUpdateTime());
        chatRoom.modifyChatRoomDeleteTime(chatRoomDTO.getChatRoomDeleteTime());
    }

    /* 3. 채팅 내용 DB 저장 */
    @Transactional
    public void registerChatting(InsertChattingDTO insertChattingDTO) {
        chattingRepository.save(chatModelMapper.map(insertChattingDTO, Chatting.class));
    }

    /* 4. 채팅 내용 수정 */
    @Transactional
    public void modifyChatting(ModifyChattingDTO modifyChattingDTO) {
        Chatting chatting = chattingRepository.findById(modifyChattingDTO.getChattingUniqueNum()).orElseThrow(IllegalArgumentException::new);
        /* 채팅 수정 메소드 */
        chatting.modifyChatting(modifyChattingDTO.getChattingContent(),
                                modifyChattingDTO.getChattingUpdateTime(),
                                modifyChattingDTO.getChattingState());
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
        return sqlSession.getMapper(ChattingMapper.class).selectChatRoomChatting(chatRoomUniqueNum);
    }

    /* 7. 채팅 이미지 업로드 */
    public void registerImageUpload() {

    }

    /* 8. 채팅 동영상 업로드 */
    public void registerVideoUpload() {

    }

    /* 9. 채팅 파일 조회  */


    /* 10. 투표 등록 */
    public void registerVote() {

    }

    /* 11. 투표 수정 */
    public void modifyVote() {

    }

    /* 12. 투표 삭제 */
    public void deleteVote() {

    }

    /* 13. 투표 조회 */

}
