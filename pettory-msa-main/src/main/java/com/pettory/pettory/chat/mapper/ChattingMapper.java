package com.pettory.pettory.chat.mapper;

import com.pettory.pettory.chat.dto.chatting.SelectChattingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChattingMapper {
    List<SelectChattingDTO> selectChatRoomChatting(Integer chatRoomUniqueNum);
}
