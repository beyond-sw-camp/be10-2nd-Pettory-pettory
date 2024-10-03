package com.pettory.pettory.board.command.application.controller;

import com.pettory.pettory.board.command.application.dto.PostRequest;
import com.pettory.pettory.board.command.application.dto.PostUpdateRequest;
import com.pettory.pettory.board.command.application.service.BoardPostDeleteService;
import com.pettory.pettory.board.command.application.service.BoardPostInsertService;
import com.pettory.pettory.board.command.application.service.BoardPostUpdateService;
import com.pettory.pettory.board.command.application.service.FileService;
import com.pettory.pettory.board.query.dto.BoardPostDetailDTO;
import com.pettory.pettory.board.query.dto.BoardPostDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시글", description = "게시글 등록/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardPostCRUDController {

    private final BoardPostInsertService boardPostInsertService;
    private final BoardPostUpdateService boardPostUpdateService;
    private final BoardPostDeleteService boardPostDeleteService;
    private final FileService fileService;

    @Operation(summary = "게시글 등록", description = "회원이 게시글을 등록한다.")
    @ApiResponse(responseCode = "201", description = "게시글 등록 성공")
    @PostMapping("/posts")
    public ResponseEntity<BoardPostDetailResponse> createPost(
            @Valid @RequestBody PostRequest postRequest  // 게시글 데이터
//            @RequestPart(value = "files", required = false) List<MultipartFile> files  // 파일 데이터는 선택적
    ) {
        // 1. 게시글 생성
        int postNum = boardPostInsertService.createPost(postRequest);

//        // 2. 파일 저장
//        if (files != null && !files.isEmpty()) {
//            try {
//                fileService.saveFiles(files, (long) postNum);  // 게시글 번호를 기반으로 파일 저장
//            } catch (IOException e) {
//                return ResponseEntity.status(500).body(null);  // 파일 저장 중 오류 발생 시 처리
//            }
//        }

        // 3. 게시글 세부 정보 반환
        List<BoardPostDetailDTO> postDetailDTOList = boardPostInsertService.getPostDetails(postNum);
        if (postDetailDTOList == null || postDetailDTOList.isEmpty()) {
            return ResponseEntity.status(404).body(null);  // 게시글이 존재하지 않는 경우 처리
        }

//        List<String> fileLinks = fileService.getFileLinksByPostId((long) postNum);
//        if (fileLinks == null) {
//            fileLinks = new ArrayList<>();  // 파일이 없을 경우 빈 리스트 반환
//        }

        // BoardPostDetailResponse 객체 생성
        BoardPostDetailResponse response = BoardPostDetailResponse.builder()
                .postDetail(postDetailDTOList)
//                .fileLinks(fileLinks)
                .build();

        // 201 Created 응답 반환
        return ResponseEntity.status(201).body(response);
    }


    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "회원이 게시글을 수정한다.")
    @ApiResponse(responseCode = "201", description = "게시글 수정 성공")
    @PutMapping("/posts/{postNum}")
    public ResponseEntity<BoardPostDetailResponse> updatePost(
            @PathVariable int postNum,                     // 수정할 게시글 번호
            @Valid @RequestBody PostUpdateRequest postUpdateRequest  // 수정된 게시글 데이터
//            @RequestPart("files") List<MultipartFile> files       // 수정된 파일 데이터 (선택)
    ) {
        // 1. 게시글 수정
        boardPostUpdateService.updatePost(postNum, postUpdateRequest);

//        // 2. 파일 수정 (파일이 있을 경우만 처리)
//        try {
//            if (files != null && !files.isEmpty()) {
//                fileService.saveFiles(files, (long) postNum);  // 게시글 번호를 기반으로 파일 저장 (수정)
//            }
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body(null);  // 파일 저장 중 오류 발생 시 처리
//        }

        // 3. 게시글 세부 정보 반환 (수정 후의 내용 반환)
        List<BoardPostDetailDTO> postDetailDTOList = boardPostInsertService.getPostDetails(postNum);  // 게시글 세부 정보 가져오기
//        List<String> fileLinks = fileService.getFileLinksByPostId((long)postNum);  // 파일 링크 가져오기

        // BoardPostDetailResponse 객체 생성
        BoardPostDetailResponse response = BoardPostDetailResponse.builder()
                .postDetail(postDetailDTOList)
//                .fileLinks(fileLinks)
                .build();

        // 200 OK 응답 반환
        return ResponseEntity.ok(response);
    }


    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "회원이 게시글을 삭제한다.")
    @ApiResponse(responseCode = "201", description = "게시글 삭제 성공")
    @DeleteMapping("/posts/{postNum}")
    public ResponseEntity<Void> deletePost(@PathVariable int postNum) {
        // 1. 게시글 삭제 (상태를 DELETE로 변경)
        boardPostDeleteService.deletePost(postNum);

        // 2. 204 No Content 응답 반환 (삭제된 경우)
        return ResponseEntity.noContent().build();
    }


}
