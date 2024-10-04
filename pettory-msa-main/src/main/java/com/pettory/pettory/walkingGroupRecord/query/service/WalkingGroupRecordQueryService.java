package com.pettory.pettory.walkingGroupRecord.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordDTO;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordDetailResponse;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordListResponse;
import com.pettory.pettory.walkingGroupRecord.query.mapper.WalkingGroupRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingGroupRecordQueryService {

    private final WalkingGroupRecordMapper walkingGroupRecordMapper;

    @Transactional(readOnly = true)
    public WalkingGroupRecordListResponse getWalkingGroupRecords(Integer page, Integer size, LocalDate walkingGroupDate, String walkingGroupRecordState) {

        int offset = (page - 1) * size;

        List<WalkingGroupRecordDTO> walkingGroupRecords = walkingGroupRecordMapper.selectWalkingGroupRecords(offset, size, walkingGroupDate, walkingGroupRecordState);

        long totalItems = walkingGroupRecordMapper.countWalkingGroupRecords(walkingGroupDate, walkingGroupRecordState);

        return WalkingGroupRecordListResponse.builder()
                .walkingGroupRecords(walkingGroupRecords)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size ))
                .totalItems(totalItems)
                .build();


    }

    @Transactional(readOnly = true)
    public WalkingGroupRecordDetailResponse getWalkingGroupRecordById(int walkingGroupId) {

        List<WalkingGroupRecordDTO> walkingGroupRecord = walkingGroupRecordMapper.selectWalkingGroupRecordById(walkingGroupId);

        if(walkingGroupRecord == null) {
            throw new NotFoundException("해당 아이디를 가진 산책모임기록이 없습니다. 아이다 : " + walkingGroupId);
        }

        return new WalkingGroupRecordDetailResponse(walkingGroupRecord);


    }
}
