package com.pettory.pettory.board.command.application.controller;

import com.pettory.pettory.board.command.application.dto.CategoryRequest;
import com.pettory.pettory.board.command.application.dto.CategoryUpdateRequest;
import com.pettory.pettory.board.command.application.service.BoardCategoryDeleteService;
import com.pettory.pettory.board.command.application.service.BoardCategoryInsertService;
import com.pettory.pettory.board.command.application.service.BoardCategoryUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "카테고리", description = "카테고리 등록/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCategoryCRUDController {

    private final BoardCategoryInsertService boardCategoryInsertService;
    private final BoardCategoryUpdateService boardCategoryUpdateService;
    private final BoardCategoryDeleteService boardCategoryDeleteService;


    // 카테고리 생성
    @Operation(summary = "카테고리 등록", description = "회원이 카테고리을 등록한다.")
    @ApiResponse(responseCode = "201", description = "카테고리 등록 성공")
    @PostMapping("/categorys")
    public ResponseEntity<String> createCategory(
            @RequestBody CategoryRequest categoryRequest) {

        // 1. 카테고리 생성 서비스 호출
        int categoryNum = boardCategoryInsertService.createCategory(categoryRequest);

        // 2. 성공 메시지 반환
        return ResponseEntity.status(201).body("카테고리가 성공적으로 생성되었습니다. 카테고리 번호: " + categoryNum);
    }

    // 카테고리 수정
    @Operation(summary = "카테고리 수정", description = "회원이 카테고리을 수정한다.")
    @ApiResponse(responseCode = "201", description = "카테고리 수정 성공")
    @PutMapping("/categorys/{categoryNum}")
    public ResponseEntity<String> updateCategory(
            @PathVariable int categoryNum,
            @RequestBody CategoryUpdateRequest categoryUpdateRequest) {

        // 1. 카테고리 수정 서비스 호출
        boardCategoryUpdateService.updateCategory(categoryNum, categoryUpdateRequest);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("카테고리가 성공적으로 수정되었습니다.");
    }

    // 카테고리 삭제
    @Operation(summary = "카테고리 삭제", description = "회원이 카테고리을 삭제한다.")
    @ApiResponse(responseCode = "201", description = "카테고리 삭제 성공")
    @DeleteMapping("/categorys/{categoryNum}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryNum) {

        // 1. 카테고리 삭제 서비스 호출 (하드 삭제)
        boardCategoryDeleteService.deleteCategory(categoryNum);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("카테고리가 성공적으로 삭제되었습니다.");
    }




}
