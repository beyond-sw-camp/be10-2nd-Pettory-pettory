package com.pettory.pettory.board.command.application.controller;

import com.pettory.pettory.board.command.application.dto.CommentRequest;
import com.pettory.pettory.board.command.application.dto.CommentUpdateRequest;
import com.pettory.pettory.board.command.application.service.BoardCommentDeleteService;
import com.pettory.pettory.board.command.application.service.BoardCommentInsertService;
import com.pettory.pettory.board.command.application.service.BoardCommentUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/comments")
public class BoardCommentCRUDController {

    private final BoardCommentInsertService boardCommentInsertService;
    private final BoardCommentUpdateService boardCommentUpdateService;
    private final BoardCommentDeleteService boardCommentDeleteService;

    // 댓글 작성
    @PostMapping("/create")
    public ResponseEntity<String> createComment(
            @RequestBody CommentRequest commentRequest) {

        // 1. 댓글 생성 서비스 호출
        int commentNum = boardCommentInsertService.createComment(commentRequest);

        // 2. 성공 메시지와 댓글 번호 반환
        return ResponseEntity.status(201).body("댓글이 성공적으로 생성되었습니다. 댓글 번호: " + commentNum);
    }

    @PutMapping("/update/{commentNum}")
    public ResponseEntity<String> updateComment(
            @PathVariable int commentNum,                         // 수정할 댓글 번호
            @Valid @RequestBody CommentUpdateRequest commentUpdateRequest // 수정된 댓글 내용
    ) {
        // 1. 댓글 수정 서비스 호출
        boardCommentUpdateService.updateComment(commentNum, commentUpdateRequest);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{commentNum}")
    public ResponseEntity<String> deleteComment(
            @PathVariable int commentNum) {

        // 1. 댓글 삭제 서비스 호출
        boardCommentDeleteService.deleteComment(commentNum);

        // 2. 성공 메시지 반환
        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}
