package com.pettory.pettory.board.query.controller;

import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import com.pettory.pettory.board.query.service.BoardCategoryListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "카테고리 조회", description = "카테고리 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCategoryController {

    private final BoardCategoryListService boardCategoryListService;

    // 카테고리 목록 조회
    @Operation(summary = "카테고리 목록 조회", description = "관리자가 카테고리 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "카테고리 목록 조회 성공")
    @GetMapping("/categorys")
    public ResponseEntity<List<BoardCategoryDTO>> getCategoryList() {

        // 서비스에서 카테고리 목록 조회
        List<BoardCategoryDTO> response = boardCategoryListService.getCategoryList();

        // 조회한 목록을 반환
        return ResponseEntity.ok(response);
    }
}
