package com.pettory.mainserver.walkingGroupApplication.query.service;

import com.pettory.mainserver.exception.NotFoundException;
import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationDTO;
import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationDetailResponse;
import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationListResponse;
import com.pettory.mainserver.walkingGroupApplication.query.mapper.WalkingGroupApplicationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingGroupApplicationQueryService {

    private final WalkingGroupApplicationMapper walkingGroupApplicationMapper;

    @Transactional(readOnly = true)
    public WalkingGroupApplicationListResponse getWalkingGroupApplications(Integer page, Integer size, String walkingGroupApprovalState) {
        int offset = (page - 1) * size;
        List<WalkingGroupApplicationDTO> walkingGroupApplications = walkingGroupApplicationMapper.selectWalkingGroupApplications(offset, size, walkingGroupApprovalState);

        long totalItems = walkingGroupApplicationMapper.countWalkingGroupApplications(walkingGroupApprovalState);

        return WalkingGroupApplicationListResponse.builder()
                .walkingGroupApplications(walkingGroupApplications)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    @Transactional(readOnly = true)
    public WalkingGroupApplicationDetailResponse getWalkingGroupApplication(int walkingGroupId) {
        List<WalkingGroupApplicationDTO> walkingGroupById = walkingGroupApplicationMapper.selectWalkingGroupById(walkingGroupId);

        if(walkingGroupById == null) {
            throw new NotFoundException("해당 코드를 가진 산책모임신청을 찾을 수 없습니다. 코드 : " + walkingGroupId);
        }

        return new WalkingGroupApplicationDetailResponse(walkingGroupById);
    }

}
