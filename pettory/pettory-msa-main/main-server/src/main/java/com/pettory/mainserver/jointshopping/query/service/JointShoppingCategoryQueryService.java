package com.pettory.mainserver.jointshopping.query.service;

import com.pettory.mainserver.jointshopping.query.dto.JointShoppingCategoryDTO;
import com.pettory.mainserver.jointshopping.query.dto.JointShoppingCategoryListResponse;
import com.pettory.mainserver.jointshopping.query.mapper.JointShoppingCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingCategoryQueryService {

    private final JointShoppingCategoryMapper jointShoppingCategoryMapper;

    /* 카테고리 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingCategoryListResponse getCategorys(String categoryTitle) {

        List<JointShoppingCategoryDTO> categorys = jointShoppingCategoryMapper.selectCategorys(categoryTitle);

        long totalItems = jointShoppingCategoryMapper.countCategorys(categoryTitle);

        return JointShoppingCategoryListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .categoryList(categorys)
                .totalItems(totalItems)
                .build();
    }
}
