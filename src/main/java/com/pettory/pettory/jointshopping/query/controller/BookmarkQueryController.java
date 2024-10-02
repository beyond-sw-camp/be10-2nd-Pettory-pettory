package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.*;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Bookmark")
@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class BookmarkQueryController {

    private final JointShoppingGroupQueryService jointShoppingGroupQueryService;

    @Operation(summary = "즐겨찾기된 모임 목록 조회")
    @ApiResponse(responseCode = "200", description = "즐겨찾기된 모임 목록 조회 성공")
    @GetMapping("/bookmarks")
    public ResponseEntity<JointShoppingGroupListResponse> getBookmarks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getBookmarks(page, size, userId);

        return ResponseEntity.ok(response);
    }

}

