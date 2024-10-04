package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingCategoryListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingCategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공동구매 카테고리")
@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingCategoryQueryController {

    private final JointShoppingCategoryQueryService jointShoppingCategoryQueryService;

    @Operation(summary = "카테고리 목록 조회", description = "카테고리 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "카테고리 목록 조회 성공")
    @GetMapping("/categories")
    public ResponseEntity<JointShoppingCategoryListResponse> getCategorys(
            @RequestParam(required = false) String categoryTitle
    ) {

        JointShoppingCategoryListResponse response= jointShoppingCategoryQueryService.getCategorys(categoryTitle);

        return ResponseEntity.ok(response);
    }

}
