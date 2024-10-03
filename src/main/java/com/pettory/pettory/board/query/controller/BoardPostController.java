package com.pettory.pettory.board.query.controller;

import com.pettory.pettory.board.query.dto.BoardPostDetailResponse;
import com.pettory.pettory.board.query.dto.BoardPostListResponse;
import com.pettory.pettory.board.query.service.BoardPostIdService;
import com.pettory.pettory.board.query.service.BoardPostListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardPostController {

    private final BoardPostListService boardPostListService;
    private final BoardPostIdService boardPostIdService;

    // 게시글 목록 조회
    @GetMapping("/posts")
    public ResponseEntity<BoardPostListResponse> getPosts(){
        BoardPostListResponse response = boardPostListService.getPostList();

        return ResponseEntity.ok(response);
    }

    // 게시글 내용 상세 조회
    @GetMapping("/posts/{postNum}")
    public ResponseEntity<BoardPostDetailResponse> getPostDetail(
            @PathVariable int postNum
    ) {

        BoardPostDetailResponse response = boardPostIdService.getPostsById(postNum);

        return ResponseEntity.ok(response);

    }
}
