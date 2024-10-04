package com.pettory.mainserver.chat.mapper;

import com.pettory.mainserver.chat.dto.vote.SelectVoteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper {
    List<SelectVoteDTO> selectVoteList(Integer voteChatroomUniqueNum);
}
