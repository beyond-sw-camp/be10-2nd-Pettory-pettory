package com.pettory.pettory.jointshopping.query.service;

import com.pettory.pettory.jointshopping.exception.NotFoundException;
import com.pettory.pettory.jointshopping.query.dto.GroupDetailResponse;
import com.pettory.pettory.jointshopping.query.dto.GroupDTO;
import com.pettory.pettory.jointshopping.query.dto.GroupListResponse;
import com.pettory.pettory.jointshopping.query.mapper.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupQueryService {

    private final GroupMapper groupMapper;

    /* 공동구매모임 목록 조회 */
    @Transactional(readOnly = true)
    public GroupListResponse getGroups(Integer page, Integer size, Long categoryNum, String groupName) {
        int offset = (page - 1) * size;
        List<GroupDTO> groups = groupMapper.selectGroups(offset, size, categoryNum, groupName);

        long totalItems = groupMapper.countGroups(categoryNum, groupName);

        return GroupListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupList(groups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /* 공동구매모임 상세 조회  */
    @Transactional(readOnly = true)
    public GroupDetailResponse getGroup(Long groupNum) {
        GroupDTO group = groupMapper.selectGroupByNum(groupNum);

        if (group == null) {
            throw new NotFoundException("해당 코드를 가진 상품을 찾지 못했습니다. 상품 코드 : " + groupNum);
        }

        return new GroupDetailResponse(group);
    }
}
