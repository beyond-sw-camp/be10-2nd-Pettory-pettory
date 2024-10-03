package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingCategoryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Joint-Shopping-Category")
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingCategoryCommandController {

    private final JointShoppingCategoryApplicationService jointShoppingCategoryApplicationService;

    @Operation(summary = "카테고리 등록")
    @ApiResponse(responseCode = "201", description = "카테고리 등록 성공")
    @PostMapping("/categories")
    public ResponseEntity<CommonResponseDTO> createCategory(
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {
        Long jointShoppingCategoryNum = jointShoppingCategoryApplicationService.createCategory(categoryRequest);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "카테고리 등록 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "카테고리 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "카테고리 수정 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 카테고리 번호")
    })
    @PutMapping("/categories/{jointShoppingCategoryNum}")
    public ResponseEntity<CommonResponseDTO> updateCategory(
            @PathVariable final Long jointShoppingCategoryNum,
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {

        jointShoppingCategoryApplicationService.updateCategory(jointShoppingCategoryNum, categoryRequest);;
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "카테고리 수정 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "카테고리 삭제")
    @ApiResponse(responseCode = "204", description = "카테고리 삭제 성공")
    @DeleteMapping("/categories/{jointShoppingCategoryNum}")
    public ResponseEntity<CommonResponseDTO> deleteCategory(@PathVariable final Long jointShoppingCategoryNum) {

        jointShoppingCategoryApplicationService.deleteCategory(jointShoppingCategoryNum);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.NO_CONTENT.value(), "카테고리 삭제 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
    }
}
