package com.pettory.mainserver.walkingGroupRecord.query.mapper;

import com.pettory.mainserver.walkingGroupRecord.query.dto.WalkingGroupRecordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface WalkingGroupRecordMapper {
    List<WalkingGroupRecordDTO> selectWalkingGroupRecords(
            @Param("offset") int offset,
            @Param("limit") Integer limit,
            @Param("walkingGroupDate") LocalDate walkingGroupDate,
            @Param("walkingGroupRecordState") String walkingGroupRecordState
    );

    long countWalkingGroupRecords(
            @Param("walkingGroupDate") LocalDate walkingGroupDate,
            @Param("walkingGroupRecordState") String walkingGroupRecordState
    );

    List<WalkingGroupRecordDTO> selectWalkingGroupRecordById(@Param("walkingGroupId") int walkingGroupId);
}
