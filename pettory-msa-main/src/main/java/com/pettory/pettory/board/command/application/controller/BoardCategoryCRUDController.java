package com.pettory.pettory.board.command.application.controller;

import com.pettory.pettory.board.command.application.dto.CategoryRequest;
import com.pettory.pettory.board.command.application.dto.CategoryUpdateRequest;
import com.pettory.pettory.board.command.application.service.BoardCategoryDeleteService;
import com.pettory.pettory.board.command.application.service.BoardCategoryInsertService;
import com.pettory.pettory.board.command.application.service.BoardCategoryUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/category")
public class BoardCategoryCRUDController {

    private final BoardCategoryInsertService boardCategoryInsertService;
    private final BoardCategoryUpdateService boardCategoryUpdateService;
    private final BoardCategoryDeleteService boardCategoryDeleteService;


    // 카테고리 생성
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(
            @RequestBody CategoryRequest categoryRequest) {

        // 1. 카테고리 생성 서비스 호출
        int categoryNum = boardCategoryInsertService.createCategory(categoryRequest);

        // 2. 성공 메시지 반환
        return ResponseEntity.status(201).body("카테고리가 성공적으로 생성되었습니다. 카테고리 번호: " + categoryNum);
    }

    // 카테고리 수정
    @PutMapping("/update/{categoryNum}")
    public ResponseEntity<String> updateCategory(
            @PathVariable int categoryNum,
            @RequestBody CategoryUpdateRequest categoryUpdateRequest) {

        // 1. 카테고리 수정 서비스 호출
        boardCategoryUpdateService.updateCategory(categoryNum, categoryUpdateRequest);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("카테고리가 성공적으로 수정되었습니다.");
    }

    // 카테고리 삭제
    @DeleteMapping("/delete/{categoryNum}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryNum) {

        // 1. 카테고리 삭제 서비스 호출 (하드 삭제)
        boardCategoryDeleteService.deleteCategory(categoryNum);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("카테고리가 성공적으로 삭제되었습니다.");
    }




}
