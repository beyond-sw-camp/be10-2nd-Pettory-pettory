package com.pettory.pettory.jointshopping.query.service;

import com.pettory.pettory.jointshopping.exception.NotFoundException;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDetailResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDTO;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupListResponse;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingGroupQueryService {

    private final JointShoppingGroupMapper jointShoppingGroupMapper;

    /* 공동구매모임 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingGroupListResponse getGroups(Integer page, Integer size, Long categoryNum, String groupName) {
        int offset = (page - 1) * size;
        List<JointShoppingGroupDTO> groups = jointShoppingGroupMapper.selectGroups(offset, size, categoryNum, groupName);

        long totalItems = jointShoppingGroupMapper.countGroups(categoryNum, groupName);

        return JointShoppingGroupListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupList(groups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /* 공동구매모임 상세 조회  */
    @Transactional(readOnly = true)
    public JointShoppingGroupDetailResponse getGroup(Long groupNum) {
        JointShoppingGroupDTO group = jointShoppingGroupMapper.selectGroupByNum(groupNum);

        if (group == null) {
            throw new NotFoundException("해당 코드를 가진 상품을 찾지 못했습니다. 상품 코드 : " + groupNum);
        }

        return new JointShoppingGroupDetailResponse(group);
    }
}
