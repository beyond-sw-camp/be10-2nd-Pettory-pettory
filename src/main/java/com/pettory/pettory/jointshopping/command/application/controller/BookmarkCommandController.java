package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.BookmarkRequest;
import com.pettory.pettory.jointshopping.command.application.service.BookmarkApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> createBookmark(
            @RequestBody @Valid BookmarkRequest bookmarkRequest
    ) {
        Long bookmarkNum = bookmarkApplicationService.createBookmark(bookmarkRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/bookmarks/" + bookmarkNum))
                .build();
    }

    @Operation(summary = "즐겨찾기 삭제")
    @ApiResponse(responseCode = "204", description = "즐겨찾기 삭제 성공")
    @DeleteMapping("/bookmarks/{bookmarkNum}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable final Long bookmarkNum) {

        bookmarkApplicationService.deleteBookmark(bookmarkNum);

        return ResponseEntity.noContent().build();
    }
}
