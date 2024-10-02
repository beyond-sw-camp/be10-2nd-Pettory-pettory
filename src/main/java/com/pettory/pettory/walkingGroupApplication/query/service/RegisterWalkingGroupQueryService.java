package com.pettory.pettory.walkingGroupApplication.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupDTO;
import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupDetailResponse;
import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupListResponse;
import com.pettory.pettory.walkingGroupApplication.query.mapper.RegisterWalkingGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterWalkingGroupQueryService {

    private final RegisterWalkingGroupMapper registerWalkingGroupMapper;

    @Transactional(readOnly = true)
    public RegisterWalkingGroupListResponse getRegisterWalkingGroups(Integer page, Integer size, int walkingGroupId) {
        int offset = (page - 1) * size;
        List<RegisterWalkingGroupDTO> registerWalkingGroups =  registerWalkingGroupMapper.selectRegisterWalkingGroups(
                offset, size, walkingGroupId
        );

        long totalItems = registerWalkingGroupMapper.countRegisterWalkingGroups(walkingGroupId);

        return RegisterWalkingGroupListResponse.builder()
                .registerWalkingGroups(registerWalkingGroups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();

    }

    @Transactional(readOnly = true)
    public RegisterWalkingGroupDetailResponse getRegisterWalkingGroup(int userId) {
        List<RegisterWalkingGroupDTO> registerWalkingGroupById = registerWalkingGroupMapper.selectRegisterWalkingGroupById(userId);

        if(registerWalkingGroupById == null) {
            throw new NotFoundException("해당 아이디를 가진 회원을 찾을 수 없습니다. id : " + userId);
        }

        return new RegisterWalkingGroupDetailResponse(registerWalkingGroupById);
    }
}
