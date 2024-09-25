package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingCategoryDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JointShoppingCategoryApplicationService {

    private final JointShoppingCategoryDomainService jointShoppingCategoryDomainService;

    /* 카테고리 등록 */
    @Transactional
    public Long createCateogory(JointShoppingCategoryRequest categoryRequest) {

        /* jointshoppingcategory 도메인 로직 실행, entity 반환 */
        JointShoppingCategory newJointShoppingCategory = jointShoppingCategoryDomainService.createCategory(categoryRequest);

        /* save 로직 실행 */
        JointShoppingCategory jointShoppingCategory = jointShoppingCategoryDomainService.saveCategory(newJointShoppingCategory);

        /* 등록된 번호 반환 */
        return jointShoppingCategory.getJointShoppingCategoryNum();
    }

    /* 카테고리 수정 */
    @Transactional
    public void updateCategory(Long jointShoppingCategoryNum, JointShoppingCategoryRequest categoryRequest) {
        jointShoppingCategoryDomainService.updateCategory(jointShoppingCategoryNum, categoryRequest);
    }

    /* 카테고리 삭제 */
    @Transactional
    public void deleteCategory(Long jointShoppingCategoryNum) {
        jointShoppingCategoryDomainService.deleteCategory(jointShoppingCategoryNum);
    }


}
