package com.pettory.pettory.walkinggroup.query.mapper;

import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WalkingGroupMapper {

    List<WalkingGroupDTO> selectWalkingGroups(
            @Param("offset") int offset, @Param("limit") int limit,
            @Param("walkingGroupName") String walkingGroupName,
            @Param("walkingGroupInfo") String walkingGroupInfo
    );

    long countWalkingGroups(
            @Param("walkingGroupName") String walkingGroupName,
            @Param("walkingGroupInfo") String walkingGroupInfo
    );

    WalkingGroupDTO selectWalkingGroupById(@Param("walkingGroupId") int walkingGroupId);
}
