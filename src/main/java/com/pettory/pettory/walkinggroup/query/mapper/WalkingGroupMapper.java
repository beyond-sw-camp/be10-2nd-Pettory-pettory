package com.pettory.pettory.walkinggroup.query.mapper;

import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WalkingGroupMapper {

    List<WalkingGroupDTO> selectWalkingGroups(
            @Param("offset") int offset, @Param("limit") int limit,
            @Param("walkingGroupInfo") String walkingGroupInfo
    );

    long countWalkingGroups(@Param("walkingGroupInfo") String walkingGroupInfo);

    List<WalkingGroupDTO> selectWalkingGroupByName(@Param("walkingGroupName") String walkingGroupName);
}
