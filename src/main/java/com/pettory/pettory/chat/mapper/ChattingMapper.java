package com.pettory.pettory.chat.mapper;

import com.pettory.pettory.chat.dto.chatting.SelectChattingDTO;

import java.util.List;

public interface ChattingMapper {
    List<SelectChattingDTO> selectChatRoomChatting(Integer chatRoomUniqueNum);
}
