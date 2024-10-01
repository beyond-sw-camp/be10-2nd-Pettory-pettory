package com.pettory.pettory.comment.controller;

import com.pettory.pettory.comment.entity.BoardCommentEntity;
import com.pettory.pettory.comment.service.BoardCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class BoardCommentController {

    private final BoardCommentService boardCommentService;

    @Autowired
    public BoardCommentController(BoardCommentService boardCommentService) {
        this.boardCommentService = boardCommentService;
    }

    @PostMapping("/create")
    public ResponseEntity<BoardCommentEntity> createComment(@RequestParam int postNum, @RequestParam String content) {
        return new ResponseEntity<>(boardCommentService.createComment(postNum, content), HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentNum}")
    public ResponseEntity<BoardCommentEntity> updateComment(@PathVariable int commentNum, @RequestParam String content) {
        return new ResponseEntity<>(boardCommentService.updateComment(commentNum, content), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentNum}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentNum) {
        boardCommentService.deleteComment(commentNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reply")
    public ResponseEntity<BoardCommentEntity> createSubComment(@RequestParam int parentCommentNum, @RequestParam String content) {
        return new ResponseEntity<>(boardCommentService.createSubComment(parentCommentNum, content), HttpStatus.CREATED);
    }
}

