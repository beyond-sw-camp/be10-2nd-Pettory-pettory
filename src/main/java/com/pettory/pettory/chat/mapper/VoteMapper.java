package com.pettory.pettory.chat.mapper;

import com.pettory.pettory.chat.dto.vote.SelectVoteDTO;

import java.util.List;

public interface VoteMapper {
    List<SelectVoteDTO> selectVoteList(Integer voteChatroomUniqueNum);
}
