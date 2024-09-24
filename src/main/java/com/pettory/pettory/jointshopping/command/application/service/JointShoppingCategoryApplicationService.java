package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingCategoryDomainService;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingGroupDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JointShoppingCategoryApplicationService {

    private final JointShoppingCategoryDomainService jointShoppingCategoryDomainService;

    /* 카테고리 등록 */
    public Long createCateogory(JointShoppingCategoryRequest categoryRequest) {

        /* jointshoppingcategory 도메인 로직 실행, entity 반환 */
        JointShoppingCategory newJointShoppingCategory = jointShoppingCategoryDomainService.createCategory(categoryRequest);

        /* save 로직 실행 */
        JointShoppingCategory jointShoppingCategory = jointShoppingCategoryDomainService.saveCategory(newJointShoppingCategory);

        /* 등록된 번호 반환 */
        return jointShoppingCategory.getJointShoppingCategoryNum();
    }

    /* 카테고리 수정 */
    public void updateCategory(Long jointShoppingCategoryNum, JointShoppingCategoryRequest categoryRequest) {
        jointShoppingCategoryDomainService.updateCategory(jointShoppingCategoryNum, categoryRequest);
    }

    /* 카테고리 삭제 */
    public void deleteCategory(Long jointShoppingCategoryNum) {
        jointShoppingCategoryDomainService.deleteCategory(jointShoppingCategoryNum);
    }


}
