package com.pettory.pettory.jointshopping.query.service;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingGroupMapper;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingParticipationMapper;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationQueryService {

    private final JointShoppingParticipationMapper jointShoppingParticipationMapper;

    /* 현재 공동구매모임 참가의 전체 사용자 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingUserListResponse getParticipants(Integer page, Integer size, Long groupNum) {
        int offset = (page - 1) * size;
        List<UserInfoResponse> Users = jointShoppingParticipationMapper.selectParticipants(offset, size, groupNum);

        long totalItems = jointShoppingParticipationMapper.countParticipants(groupNum);

        return JointShoppingUserListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupUserList(Users)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }
}
