package com.pettory.pettory.jointshopping.query.mapper;

import com.pettory.pettory.user.query.dto.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JointShoppingParticipationMapper {
    List<UserInfoResponse> selectParticipants(@Param("offset") int offset, @Param("limit") int limit, @Param("groupNum") Long groupNum);

    long countParticipants(@Param("groupNum") Long groupNum);
}
