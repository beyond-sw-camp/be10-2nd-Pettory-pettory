package com.pettory.mainserver.walkingGroupApplication.query.mapper;

import com.pettory.mainserver.walkingGroupApplication.query.dto.RegisterWalkingGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegisterWalkingGroupMapper {
    List<RegisterWalkingGroupDTO> selectRegisterWalkingGroups(
            @Param("offset") int offset, @Param("limit") int limit, @Param("walkingGroupId") int walkingGroupId);

    long countRegisterWalkingGroups(@Param("walkingGroupId") int walkingGroupId);

    List<RegisterWalkingGroupDTO> selectRegisterWalkingGroupById(@Param("userId") int userId);
}
