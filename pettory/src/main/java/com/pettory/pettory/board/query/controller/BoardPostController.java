package com.pettory.pettory.board.query.controller;

import com.pettory.pettory.board.query.dto.BoardPostDetailResponse;
import com.pettory.pettory.board.query.dto.BoardPostListResponse;
import com.pettory.pettory.board.query.service.BoardPostIdService;
import com.pettory.pettory.board.query.service.BoardPostListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시글 조회", description = "게시글 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardPostController {

    private final BoardPostListService boardPostListService;
    private final BoardPostIdService boardPostIdService;

    // 게시글 목록 조회
    @Operation(summary = "게시글 목록 조회", description = "회원이 게시글 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공")
    @GetMapping("/posts")
    public ResponseEntity<BoardPostListResponse> getPosts(){
        BoardPostListResponse response = boardPostListService.getPostList();

        return ResponseEntity.ok(response);
    }

    // 게시글 내용 상세 조회
    @Operation(summary = "게시글 상세 조회", description = "회원이 게시글 내용을 조회한다.")
    @ApiResponse(responseCode = "200", description = "게시글 내용 조회 성공")
    @GetMapping("/posts/{postNum}")
    public ResponseEntity<BoardPostDetailResponse> getPostDetail(
            @PathVariable int postNum
    ) {

        BoardPostDetailResponse response = boardPostIdService.getPostsById(postNum);

        return ResponseEntity.ok(response);

    }
}
