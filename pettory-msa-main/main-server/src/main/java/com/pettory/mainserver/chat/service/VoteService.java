package com.pettory.mainserver.chat.service;

import com.pettory.mainserver.chat.dto.vote.InsertVoteDTO;
import com.pettory.mainserver.chat.dto.vote.ModifyVoteDTO;
import com.pettory.mainserver.chat.dto.vote.SelectVoteDTO;
import com.pettory.mainserver.chat.dto.vote.SoftDeleteVoteDTO;
import com.pettory.mainserver.chat.entity.Vote;
import com.pettory.mainserver.chat.mapper.VoteMapper;
import com.pettory.mainserver.chat.repository.VoteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private final VoteMapper voteMapper;
    private final ModelMapper chatModelMapper;
    private final VoteRepository voteRepository;

    /* 생성자 */
    public VoteService(VoteMapper voteMapper,
                       ModelMapper chatModelMapper,
                       VoteRepository voteRepository) {
        this.voteMapper = voteMapper;
        this.chatModelMapper = chatModelMapper;
        this.voteRepository = voteRepository;
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
        return voteMapper.selectVoteList(voteChatroomUniqueNum);
    }
}