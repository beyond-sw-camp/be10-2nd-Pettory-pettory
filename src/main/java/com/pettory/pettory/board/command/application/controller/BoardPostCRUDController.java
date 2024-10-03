package com.pettory.pettory.board.command.application.controller;

import com.pettory.pettory.board.command.application.dto.PostRequest;
import com.pettory.pettory.board.command.application.dto.PostUpdateRequest;
import com.pettory.pettory.board.command.application.service.BoardPostDeleteService;
import com.pettory.pettory.board.command.application.service.BoardPostInsertService;
import com.pettory.pettory.board.command.application.service.BoardPostUpdateService;
import com.pettory.pettory.board.command.application.service.FileService;
import com.pettory.pettory.board.query.dto.BoardPostDetailDTO;
import com.pettory.pettory.board.query.dto.BoardPostDetailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardPostCRUDController {

    private final BoardPostInsertService boardPostInsertService;
    private final BoardPostUpdateService boardPostUpdateService;
    private final BoardPostDeleteService boardPostDeleteService;
    private final FileService fileService;

    // 게시글 생성
    @PostMapping("/posts")
    public ResponseEntity<BoardPostDetailResponse> createPost(
            @Valid @RequestPart("post") PostRequest postRequest,  // 게시글 데이터
            @RequestPart("files") List<MultipartFile> files       // 파일 데이터
    ) {
        // 1. 게시글 생성
        int postNum = boardPostInsertService.createPost(postRequest);

        // 2. 파일 저장
        try {
            fileService.saveFiles(files, (long) postNum);  // 게시글 번호를 기반으로 파일 저장
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);  // 파일 저장 중 오류 발생 시 처리
        }

        // 3. 게시글 세부 정보 반환
        List<BoardPostDetailDTO> postDetailDTOList = boardPostInsertService.getPostDetails(postNum);  // 게시글 세부 정보 가져오기
        List<String> fileLinks = fileService.getFileLinksByPostId((long)postNum);  // 파일 링크 가져오기

        // BoardPostDetailResponse 객체 생성
        BoardPostDetailResponse response = BoardPostDetailResponse.builder()
                .postDetail(postDetailDTOList)
                .fileLinks(fileLinks)
                .build();

        // 201 Created 응답 반환
        return ResponseEntity.status(201).body(response);
    }

    // 게시글 수정
    @PutMapping("/posts/{postNum}")
    public ResponseEntity<BoardPostDetailResponse> updatePost(
            @PathVariable int postNum,                     // 수정할 게시글 번호
            @Valid @RequestPart("post") PostUpdateRequest postUpdateRequest,  // 수정된 게시글 데이터
            @RequestPart("files") List<MultipartFile> files       // 수정된 파일 데이터 (선택)
    ) {
        // 1. 게시글 수정
        boardPostUpdateService.updatePost(postNum, postUpdateRequest);

        // 2. 파일 수정 (파일이 있을 경우만 처리)
        try {
            if (files != null && !files.isEmpty()) {
                fileService.saveFiles(files, (long) postNum);  // 게시글 번호를 기반으로 파일 저장 (수정)
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);  // 파일 저장 중 오류 발생 시 처리
        }

        // 3. 게시글 세부 정보 반환 (수정 후의 내용 반환)
        List<BoardPostDetailDTO> postDetailDTOList = boardPostInsertService.getPostDetails(postNum);  // 게시글 세부 정보 가져오기
        List<String> fileLinks = fileService.getFileLinksByPostId((long)postNum);  // 파일 링크 가져오기

        // BoardPostDetailResponse 객체 생성
        BoardPostDetailResponse response = BoardPostDetailResponse.builder()
                .postDetail(postDetailDTOList)
                .fileLinks(fileLinks)
                .build();

        // 200 OK 응답 반환
        return ResponseEntity.ok(response);
    }


    // 게시글 삭제
    @DeleteMapping("/posts/{postNum}")
    public ResponseEntity<Void> deletePost(@PathVariable int postNum) {
        // 1. 게시글 삭제 (상태를 DELETE로 변경)
        boardPostDeleteService.deletePost(postNum);

        // 2. 204 No Content 응답 반환 (삭제된 경우)
        return ResponseEntity.noContent().build();
    }


}
