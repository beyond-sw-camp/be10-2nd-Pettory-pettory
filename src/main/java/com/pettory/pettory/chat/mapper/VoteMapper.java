package com.pettory.pettory.chat.mapper;

import com.pettory.pettory.chat.dto.vote.SelectVoteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper {
    List<SelectVoteDTO> selectVoteList(Integer voteChatroomUniqueNum);
}
