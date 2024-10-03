package com.pettory.pettory.board.query.controller;

import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import com.pettory.pettory.board.query.dto.BoardCommentResponse;
import com.pettory.pettory.board.query.service.BoardCategoryListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCategoryController {

    private final BoardCategoryListService boardCategoryListService;

    // 카테고리 목록 조회
    @GetMapping("/categorys")
    public ResponseEntity<List<BoardCategoryDTO>> getCategoryList() {

        // 서비스에서 카테고리 목록 조회
        List<BoardCategoryDTO> response = boardCategoryListService.getCategoryList();

        // 조회한 목록을 반환
        return ResponseEntity.ok(response);
    }
}
