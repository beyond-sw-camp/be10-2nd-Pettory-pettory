package com.pettory.pettory.jointshopping.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingCategoryApplicationService;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingCategoryListResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingCategoryQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JointShoppingCategoryServiceTest {

    @Autowired
    private JointShoppingCategoryApplicationService jointShoppingCategoryApplicationService;
    @Autowired
    private JointShoppingCategoryQueryService jointShoppingCategoryQueryService;

    /* 카테고리 등록 테스트 */
    @Test
    void testCreateCategory() {
        JointShoppingCategoryRequest jointShoppingCategoryRequest = new JointShoppingCategoryRequest(
                "강아지발"
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingCategoryApplicationService.createCategory(jointShoppingCategoryRequest)
        );
    }

    /* 카테고리 수정 테스트 */
    @Test
    void testUpdateCategory() {
        JointShoppingCategoryRequest jointShoppingCategoryRequest = new JointShoppingCategoryRequest(
                "강아지손"
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingCategoryApplicationService.updateCategory(11L, jointShoppingCategoryRequest)
        );
    }

    /* 카테고리 삭제 테스트 */
    @Test
    void testDeleteCategory() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingCategoryApplicationService.deleteCategory(11L)
        );
    }

    /* 카테고리 목록 조회 테스트 */
    @Test
    void testGetCategorys() {
        Assertions.assertDoesNotThrow(
                () -> {
                    JointShoppingCategoryListResponse response = jointShoppingCategoryQueryService.getCategorys("");
                    response.getCategoryList().forEach(System.out::println);
                }
        );
    }
}
