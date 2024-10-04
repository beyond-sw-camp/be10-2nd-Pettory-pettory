package com.pettory.pettory.jointshopping.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.jointshopping.query.dto.*;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingGroupMapper;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
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
    public JointShoppingGroupListResponse getGroups(Integer page, Integer size, Long categoryNum, String groupName, String products) {
        int offset = (page - 1) * size;
        List<JointShoppingGroupDTO> groups = jointShoppingGroupMapper.selectGroups(offset, size, categoryNum, groupName, products);

        long totalItems = jointShoppingGroupMapper.countGroups(categoryNum, groupName, products);

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
            throw new NotFoundException("해당 코드를 가진 그룹을 찾지 못했습니다. 그룹 코드 : " + groupNum);
        }

        return new JointShoppingGroupDetailResponse(group);
    }

    /* 즐겨찾기된 모임 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingGroupListResponse getBookmarks(Integer page, Integer size, Long userId) {
        int offset = (page - 1) * size;
        List<JointShoppingGroupDTO> groups = jointShoppingGroupMapper.selectBookmarks(offset, size, userId);

        long totalItems = jointShoppingGroupMapper.countBookmarks(userId);

        return JointShoppingGroupListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupList(groups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /* 현재 공동구매모임의 전체 사용자 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingUserListResponse getGroupUsers(Integer page, Integer size, Long groupNum) {
        int offset = (page - 1) * size;
        List<UserInfoResponse> Users = jointShoppingGroupMapper.selectGroupUsers(offset, size, groupNum);

        long totalItems = jointShoppingGroupMapper.countGroupUsers(groupNum);

        return JointShoppingUserListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupUserList(Users)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /* 현재 사용자가 참여한 공동구매모임 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingGroupListResponse getUserGroups(Integer page, Integer size, Long userId) {
        int offset = (page - 1) * size;
        List<JointShoppingGroupDTO> groups = jointShoppingGroupMapper.selectUserGroups(offset, size, userId);

        long totalItems = jointShoppingGroupMapper.countUserGroups(userId);

        return JointShoppingGroupListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupList(groups)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /*  공동구매 물품 배송 정보 조회(방장) */
    @Transactional(readOnly = true)
    public JointShoppingGroupDeliveryInfoResponse getDeliveryInfo(Long groupNum) {
        JointShoppingGroupDTO group = jointShoppingGroupMapper.selectGroupByNum(groupNum);

        if (group == null) {
            throw new NotFoundException("해당 코드를 가진 그룹을 찾지 못했습니다. 그룹 코드 : " + groupNum);
        }

        return JointShoppingGroupDeliveryInfoResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .hostCourierCode(group.getHostCourierCode())
                .hostInvoiceNum(group.getHostInvoiceNum())
                .build();
    }

    /* 지급기록 조회 */
    @Transactional(readOnly = true)
    public ProvisionRecordResponse getProvisionRecord(Integer page, Integer size, String provisionState) {
        int offset = (page - 1) * size;
        List<ProvisionRecordDTO> provisionRecords = jointShoppingGroupMapper.selectProvisionRecords(offset, size, provisionState);

        long totalItems = jointShoppingGroupMapper.countProvisionRecords(provisionState);

        return ProvisionRecordResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .provisionRecords(provisionRecords)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }


}
