package com.pettory.pettory.walkinggroup.command.application.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupUpdateRequest;
import com.pettory.pettory.walkinggroup.command.domain.aggregate.WalkingGroup;
import com.pettory.pettory.walkinggroup.command.domain.aggregate.WalkingGroupState;
import com.pettory.pettory.walkinggroup.command.domain.repository.WalkingGroupRepository;
import com.pettory.pettory.walkinggroup.command.mapper.WalkingGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkingGroupCommandService {

    private final WalkingGroupRepository walkingGroupRepository;

    public int createWalkingGroup(WalkingGroupCreateRequest walkingGroupRequest) {
        WalkingGroup newWalkingGroup = WalkingGroupMapper.toEntity(walkingGroupRequest);

        WalkingGroup walkingGroup = walkingGroupRepository.save(newWalkingGroup);

        return walkingGroup.getWalkingGroupId();
    }

    @Transactional
    public void updateWalkingGroup(int walkingGroupId, WalkingGroupUpdateRequest walkingGroupRequest) {

        WalkingGroup walkingGroup = walkingGroupRepository.findById(walkingGroupId)
                .orElseThrow(() -> new NotFoundException("해당 이름에 맞는 산책모임이 없습니다. name : " + walkingGroupId));

        walkingGroup.updateWalkingGroupDetails(
                walkingGroupRequest.getWalkingGroupName(),
                walkingGroupRequest.getWalkingGroupInfo(),
                walkingGroupRequest.getWalkingGroupMaximumCount(),
                WalkingGroupState.valueOf(walkingGroupRequest.getWalkingGroupState())
        );
    }

    @Transactional
    public void deleteWalkingGroup(int walkingGroupId) {
        walkingGroupRepository.deleteById(walkingGroupId);

    }
}
