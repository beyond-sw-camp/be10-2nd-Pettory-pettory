package com.pettory.pettory.board.controller;

import com.pettory.pettory.board.dto.BoardDTO;
import com.pettory.pettory.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시물 목록 조회
    @GetMapping
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 번호를 이용한 게시물 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable int id) {
        BoardDTO boardDTO = boardService.getBoardById(id);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }

    // 게시글 작성 및 파일 업로드 처리
    @PostMapping("/createpost")
    public ResponseEntity<BoardDTO> createPostWithFile(
            @RequestPart("board") BoardDTO boardDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            BoardDTO createdBoard = boardService.createPostWithFiles(boardDTO, file);
            return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 게시물 수정
    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable int id, @RequestBody BoardDTO boardDTO) {
        BoardDTO updatedBoard = boardService.updateBoard(id, boardDTO);
        return new ResponseEntity<>(updatedBoard, HttpStatus.OK);
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    // 자신이 작성한 게시글만 조회
//    @GetMapping("/my-posts")
//    public ResponseEntity<List<BoardDTO>> getMyPosts() {
//        List<BoardDTO> myPosts = boardService.getBoardsByCurrentUser();
//        return ResponseEntity.ok(myPosts);
//    }



}
