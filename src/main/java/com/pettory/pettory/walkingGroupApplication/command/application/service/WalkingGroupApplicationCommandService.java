package com.pettory.pettory.walkingGroupApplication.command.application.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationUpdateRequest;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApprovalState;
import com.pettory.pettory.walkingGroupApplication.command.domain.repository.WalkingGroupApplicationRepository;
import com.pettory.pettory.walkingGroupApplication.command.mapper.WalkingGroupApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkingGroupApplicationCommandService {

    private final WalkingGroupApplicationRepository walkingGroupApplicationRepository;

    @Transactional
    public int createWalkingGroupApplication(WalkingGroupApplicationCreateRequest walkingGroupApplicationRequest) {

        WalkingGroupApplication newWalkingGroupApplication = WalkingGroupApplicationMapper.toEntity(walkingGroupApplicationRequest);

        WalkingGroupApplication walkingGroupApplication = walkingGroupApplicationRepository.save(newWalkingGroupApplication);

        return walkingGroupApplication.getWalkingGroupId();

    }

    @Transactional
    public void updateWalkingGroupApplication(int walkingGroupApplicationId, WalkingGroupApplicationUpdateRequest walkingGroupApplicationRequest) {

        WalkingGroupApplication walkingGroupApplication = walkingGroupApplicationRepository.findById(walkingGroupApplicationId)
                .orElseThrow(() -> new NotFoundException("해당 코드에 맞는 산책모임이 없습니다. id : " + walkingGroupApplicationId));

        walkingGroupApplication.updateWalkingGroupApplicationDetails(
                WalkingGroupApprovalState.valueOf(walkingGroupApplicationRequest.getWalkingGroupApprovalState())
        );
    }

    @Transactional
    public void deleteWalkingGroupApplication(int walkingGroupApplicationId) {
        walkingGroupApplicationRepository.deleteById(walkingGroupApplicationId);
    }
}
