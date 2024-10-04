package com.pettory.mainserver.walkingGroupApplication.query.mapper;

import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WalkingGroupApplicationMapper {
    List<WalkingGroupApplicationDTO> selectWalkingGroupApplications(
            @Param("offset") int offset, @Param("limit") Integer limit,
            @Param("walkingGroupApprovalState")String walkingGroupApprovalState
    );

    long countWalkingGroupApplications(@Param("walkingGroupApprovalState") String walkingGroupApprovalState);

    List<WalkingGroupApplicationDTO> selectWalkingGroupById(
            @Param("walkingGroupId") int walkingGroupId
    );
}
