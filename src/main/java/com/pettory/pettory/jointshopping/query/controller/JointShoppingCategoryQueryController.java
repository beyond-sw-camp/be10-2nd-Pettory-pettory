package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingCategoryListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingCategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingCategoryQueryController {

    private final JointShoppingCategoryQueryService jointShoppingCategoryQueryService;

    @GetMapping("/categories")
    public ResponseEntity<JointShoppingCategoryListResponse> getCategorys(
            @RequestParam(required = false) String categoryTitle
    ) {

        JointShoppingCategoryListResponse response= jointShoppingCategoryQueryService.getCategorys(categoryTitle);

        return ResponseEntity.ok(response);
    }

}
