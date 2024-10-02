package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.vote.InsertVoteDTO;
import com.pettory.pettory.chat.dto.vote.ModifyVoteDTO;
import com.pettory.pettory.chat.dto.vote.SelectVoteDTO;
import com.pettory.pettory.chat.dto.vote.SoftDeleteVoteDTO;
import com.pettory.pettory.chat.entity.Vote;
import com.pettory.pettory.chat.mapper.VoteMapper;
import com.pettory.pettory.chat.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final SqlSessionTemplate sqlSession;
    private final ModelMapper chatModelMapper;
    private final VoteRepository voteRepository;

    /* 생성자 */
    public VoteService(ModelMapper chatModelMapper,
                       VoteRepository voteRepository,
                       SqlSessionTemplate sqlSession) {
        this.chatModelMapper = chatModelMapper;
        this.voteRepository = voteRepository;
        this.sqlSession = sqlSession;
    }

    @Transactional
    public Vote insertVote(InsertVoteDTO insertVoteDTO) {
        return voteRepository.save(chatModelMapper.map(insertVoteDTO, Vote.class));
    }

    @Transactional
    public Vote modifyVote(ModifyVoteDTO modifyVoteDTO) {
        Vote vote = voteRepository.findById(modifyVoteDTO.getVoteUniqueNum()).orElseThrow(IllegalArgumentException::new);
        /* 투표 수정 메소드 */
        vote.modifyVoteTitleContent(modifyVoteDTO.getVoteTitle(),
                modifyVoteDTO.getVoteContent(),
                modifyVoteDTO.getVoteUpdateTime(),
                modifyVoteDTO.getVoteState());

        return vote;
    }

    @Transactional
    public void softDeleteVote(SoftDeleteVoteDTO softDeleteVoteDTO) {
        Vote vote = voteRepository.findById(softDeleteVoteDTO.getVoteUniqueNum()).orElseThrow(IllegalArgumentException::new);
        vote.softDeleteVote(softDeleteVoteDTO.getVoteDeleteTime(), softDeleteVoteDTO.getVoteState());
    }

    @Transactional
    public void hardDeleteVote(Integer voteUniqueNum) {
        voteRepository.deleteById(voteUniqueNum);
    }

    public List<SelectVoteDTO> selectVoteList(Integer voteChatroomUniqueNum) {
        return sqlSession.getMapper(VoteMapper.class).selectVoteList(voteChatroomUniqueNum);
    }
}