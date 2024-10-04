package com.pettory.mainserver.walkingGroupApplication.command.application.service;

import com.pettory.mainserver.exception.NotFoundException;
import com.pettory.mainserver.walkingGroupApplication.command.application.dto.RegisterWalkingGroupUpdateRequest;
import com.pettory.mainserver.walkingGroupApplication.command.application.dto.WalkingGroupApplicationRequest;
import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;
import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroupState;
import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;
import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.WalkingGroupApprovalState;
import com.pettory.mainserver.walkingGroupApplication.command.domain.repository.RegisterWalkingGroupRepository;
import com.pettory.mainserver.walkingGroupApplication.command.domain.repository.WalkingGroupApplicationRepository;
import com.pettory.mainserver.walkingGroupApplication.command.mapper.RegisterWalkingGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterWalkingGroupCommandService {

    private final RegisterWalkingGroupRepository registerWalkingGroupRepository;

    private final WalkingGroupApplicationRepository walkingGroupApplicationRepository;

    @Transactional
    public void updateRegisterWalkingGroup(int registerWalkingGroupId, RegisterWalkingGroupUpdateRequest registerWalkingGroupUpdateRequest) {

        RegisterWalkingGroup registerWalkingGroup = registerWalkingGroupRepository.findById(registerWalkingGroupId)
                .orElseThrow(() -> new NotFoundException("해당 아이디에 맞는 가입한 산책모임이 없습니다. id : " + registerWalkingGroupId));

        registerWalkingGroup.updateRegisterWalkingGroupDetails(
                RegisterWalkingGroupState.valueOf(registerWalkingGroupUpdateRequest.getRegisterWalkingGroupState())

        );

    }

    @Transactional
    public void deleteRegisterWalkingGroup(int registerWalkingGroupId) {

        registerWalkingGroupRepository.deleteById(registerWalkingGroupId);

    }

    @Transactional
    public void acceptWalkingGroup(int walkingGroupApplicationId, WalkingGroupApplicationRequest walkingGroupApplicationRequest) {

        WalkingGroupApplication walkingGroupApplication = walkingGroupApplicationRepository.findById(walkingGroupApplicationId)
                .orElseThrow(() -> new NotFoundException("해당 아이디에 맞는 산책모임이 없습니다. id : " + walkingGroupApplicationId));

        walkingGroupApplication.updateWalkingGroupApplicationDetails(WalkingGroupApprovalState.valueOf(walkingGroupApplicationRequest.getWalkingGroupApprovalState()));

        if(walkingGroupApplication.getWalkingGroupApprovalState() == WalkingGroupApprovalState.ACCEPT){
            RegisterWalkingGroup newRegisterWalkingGroup = RegisterWalkingGroupMapper.toEntity(walkingGroupApplication);

            registerWalkingGroupRepository.save(newRegisterWalkingGroup);
        }

    }


}
