package com.pettory.userserver.family.query.mapper;

import com.pettory.userserver.family.query.dto.ReceivedInvitationResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InvitationMapper {

    List<ReceivedInvitationResponse> selectReceivedInvitations(@Param("receiverId") Long receiverId);
}

