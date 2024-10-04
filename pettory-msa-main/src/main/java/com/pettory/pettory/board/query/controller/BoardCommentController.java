package com.pettory.pettory.board.query.controller;

import com.pettory.pettory.board.query.dto.BoardCommentResponse;
import com.pettory.pettory.board.query.service.BoardCommentListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCommentController {

    private final BoardCommentListService boardCommentListService;


    // 댓글 조회
    @GetMapping("/comments/{postNum}")
    public ResponseEntity<BoardCommentResponse> getComments(
            @PathVariable int postNum
    ) {

        BoardCommentResponse response = boardCommentListService.getCommentList(postNum);

        return ResponseEntity.ok(response);
    }
}
