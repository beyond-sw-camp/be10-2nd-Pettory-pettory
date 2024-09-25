package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingCategoryRepository;
import com.pettory.pettory.jointshopping.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JointShoppingCategoryDomainService {

    private final JointShoppingCategoryRepository jointShoppingCategoryRepository;

    /* 도메인 객체를 생성하는 로직 */
    public JointShoppingCategory createCategory(JointShoppingCategoryRequest categoryRequest) {

        /* entity 생성 */
        JointShoppingCategory newJointShoppingCategory = JointShoppingCategory.create(categoryRequest.getJointShoppingCategoryTitle());

        return newJointShoppingCategory;
    }

    /* 도메인 객체를 저장하는 로직 */
    public JointShoppingCategory saveCategory(JointShoppingCategory jointShoppingCategory) {
        return jointShoppingCategoryRepository.save(jointShoppingCategory);
    }

    /* 도메인 객체를 수정하는 로직 */
    public void updateCategory(Long jointShoppingCategoryNum, JointShoppingCategoryRequest categoryRequest) {

        JointShoppingCategory jointShoppingCategory  = jointShoppingCategoryRepository.findById(jointShoppingCategoryNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 카테고리가 없습니다. code : " + jointShoppingCategoryNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingCategory.update(categoryRequest.getJointShoppingCategoryTitle());
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteCategory(Long jointShoppingCategoryNum) {
        jointShoppingCategoryRepository.deleteById(jointShoppingCategoryNum);

    }
}
