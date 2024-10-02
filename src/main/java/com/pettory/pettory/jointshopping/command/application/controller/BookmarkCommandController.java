package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.jointshopping.command.application.dto.BookmarkRequest;
import com.pettory.pettory.jointshopping.command.application.service.BookmarkApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Bookmark")
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class BookmarkCommandController {

    private final BookmarkApplicationService bookmarkApplicationService;

    @Operation(summary = "즐겨찾기 등록")
    @ApiResponse(responseCode = "201", description = "즐겨찾기 등록 성공")
    @PostMapping("/bookmarks")
    public ResponseEntity<CommonResponseDTO> createBookmark(
            @RequestBody @Valid BookmarkRequest bookmarkRequest
    ) {
        Long bookmarkNum = bookmarkApplicationService.createBookmark(bookmarkRequest);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "즐겨찾기 등록 성공", bookmarkNum);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "즐겨찾기 삭제")
    @ApiResponse(responseCode = "204", description = "즐겨찾기 삭제 성공")
    @DeleteMapping("/bookmarks/{bookmarkNum}")
    public ResponseEntity<CommonResponseDTO> deleteBookmark(@PathVariable final Long bookmarkNum) {

        bookmarkApplicationService.deleteBookmark(bookmarkNum);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.NO_CONTENT.value(), "즐겨찾기 삭제 성공", bookmarkNum);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successResponse);
    }
}
