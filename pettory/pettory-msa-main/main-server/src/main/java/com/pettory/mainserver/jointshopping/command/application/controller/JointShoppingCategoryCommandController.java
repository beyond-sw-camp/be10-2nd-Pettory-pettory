package com.pettory.mainserver.jointshopping.command.application.controller;

import com.pettory.mainserver.common.CommonResponseDTO;
import com.pettory.mainserver.jointshopping.command.application.dto.JointShoppingCategoryRequest;
import com.pettory.mainserver.jointshopping.command.application.service.JointShoppingCategoryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공동구매 카테고리")
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingCategoryCommandController {

    private final JointShoppingCategoryApplicationService jointShoppingCategoryApplicationService;

    @Operation(summary = "카테고리 등록", description = "관리자가 카테고리를 등록한다.")
    @ApiResponse(responseCode = "201", description = "카테고리 등록 성공")
    @PostMapping("/categories")
    public ResponseEntity<CommonResponseDTO> createCategory(
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {
        Long jointShoppingCategoryNum = jointShoppingCategoryApplicationService.createCategory(categoryRequest);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "카테고리 등록 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "카테고리 수정", description = "관리자가 카테고리를 수정한다.")
    @ApiResponse(responseCode = "201", description = "카테고리 수정 성공")
    @PutMapping("/categories/{jointShoppingCategoryNum}")
    public ResponseEntity<CommonResponseDTO> updateCategory(
            @PathVariable @Schema(example = "11") final Long jointShoppingCategoryNum,
            @RequestBody @Valid JointShoppingCategoryRequest categoryRequest
    ) {

        jointShoppingCategoryApplicationService.updateCategory(jointShoppingCategoryNum, categoryRequest);;
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "카테고리 수정 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "카테고리 삭제", description = "관리자가 카테고리를 삭제한다.")
    @ApiResponse(responseCode = "204", description = "카테고리 삭제 성공")
    @DeleteMapping("/categories/{jointShoppingCategoryNum}")
    public ResponseEntity<CommonResponseDTO> deleteCategory(
            @PathVariable @Schema(example = "11") final Long jointShoppingCategoryNum
    ) {

        jointShoppingCategoryApplicationService.deleteCategory(jointShoppingCategoryNum);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.NO_CONTENT.value(), "카테고리 삭제 성공", jointShoppingCategoryNum);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
    }
}
