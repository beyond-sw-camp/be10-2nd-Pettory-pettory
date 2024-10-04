package com.pettory.pettory.walkingGroupRecord.command.application.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordUpdateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.pettory.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecord;
import com.pettory.pettory.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecordState;
import com.pettory.pettory.walkingGroupRecord.command.domain.repository.WalkingGroupRecordRepository;
import com.pettory.pettory.walkingGroupRecord.command.mapper.WalkingGroupRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkingGroupRecordCommandService {

    private final WalkingGroupRecordRepository walkingGroupRecordRepository;

    @Transactional
    public int createWalkingGroupRecord(WalkingGroupRecordCreateRequest walkingGroupRecordRequest) {
        WalkingGroupRecord newWalkingGroupRecord = WalkingGroupRecordMapper.toEntity(walkingGroupRecordRequest);

        WalkingGroupRecord walkingGroupRecord = walkingGroupRecordRepository.save(newWalkingGroupRecord);

        return walkingGroupRecord.getWalkingGroupRecordId();
    }

    @Transactional
    public void updateWalkingGroupRecord(int walkingGroupRecordId, WalkingGroupRecordUpdateRequest walkingGroupRecordRequest) {

        WalkingGroupRecord walkingGroupRecord = walkingGroupRecordRepository.findById(walkingGroupRecordId)
                .orElseThrow(() -> new NotFoundException("해당 아이디에 맞는 산책모임기록이 없습니다. id : " + walkingGroupRecordId));

        walkingGroupRecord.updateWalkingGroupRecordDetails(
                walkingGroupRecordRequest.getWalkingGroupDate(),
                walkingGroupRecordRequest.getWalkingGroupRecordDuration(),
                walkingGroupRecordRequest.getWalkingGroupRouteStart(),
                walkingGroupRecordRequest.getWalkingGroupRouteEnd(),
                WalkingGroupRecordState.valueOf(walkingGroupRecordRequest.getWalkingGroupRecordState())
        );
    }

    @Transactional
    public void deleteWalkingGroupRecord(int walkingGroupRecordId) {
        walkingGroupRecordRepository.deleteById(walkingGroupRecordId);
    }
}
