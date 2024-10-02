package com.pettory.pettory.walkinggroup.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDTO;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDetailResponse;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupListResponse;
import com.pettory.pettory.walkinggroup.query.mapper.WalkingGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingGroupQueryService {

    private final WalkingGroupMapper walkingGroupMapper;

    /* 산책 모임 조회 */
    @Transactional(readOnly = true)
    public WalkingGroupListResponse getWalkingGroups(Integer page, Integer size, String walkingGroupInfo) {
        int offset = (page - 1) * size;
        List<WalkingGroupDTO> walkingGroups = walkingGroupMapper.selectWalkingGroups(offset, size, walkingGroupInfo);

        long totalItems = walkingGroupMapper.countWalkingGroups(walkingGroupInfo);

        return WalkingGroupListResponse.builder()
                .walkingGroups(walkingGroups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /* 산책 모임 상세 조회 */
    @Transactional(readOnly = true)
    public WalkingGroupDetailResponse getWalkingGroup(String walkingGroupName) {
        List<WalkingGroupDTO> walkingGroupsByName = walkingGroupMapper.selectWalkingGroupByName(walkingGroupName);

        if(walkingGroupsByName == null) {
            throw new NotFoundException("해당 이름을 가진 산책 모임을 찾지 못했습니다. 산책 모임 이름 : " + walkingGroupName);
        }

        return new WalkingGroupDetailResponse(walkingGroupsByName);

    }
}
