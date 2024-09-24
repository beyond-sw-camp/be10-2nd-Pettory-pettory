package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupCreateRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingCategoryApplicationService;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingCategoryCommandController {

    private final JointShoppingCategoryApplicationService jointShoppingCategoryApplicationService;

    /* 카테고리 등록 */
    @PostMapping("/category")
    public ResponseEntity<Void> createGroup(
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {
        Long jointShoppingCategoryNum = jointShoppingCategoryApplicationService.createCateogory(categoryRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/category/" + jointShoppingCategoryNum))
                .build();
    }

    /* 카테고리 수정 */
    @PutMapping("/category/{jointShoppingCategoryNum}")
    public ResponseEntity<Void> updateCategory(
            @PathVariable Long jointShoppingCategoryNum,
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {

        jointShoppingCategoryApplicationService.updateCategory(jointShoppingCategoryNum, categoryRequest);

        return ResponseEntity.created(URI.create("/jointshopping/category/" + jointShoppingCategoryNum)).build();

    }

    /* 상품 삭제 */
    @DeleteMapping("/category/{jointShoppingCategoryNum}")
    public ResponseEntity<Void> deleteCategory(@PathVariable final Long jointShoppingCategoryNum) {

        jointShoppingCategoryApplicationService.deleteCategory(jointShoppingCategoryNum);

        return ResponseEntity.noContent().build();
    }
}
