package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.BookmarkRequest;
import com.pettory.pettory.jointshopping.command.application.service.BookmarkApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class BookmarkCommandController {

    private final BookmarkApplicationService bookmarkApplicationService;

    /* 즐겨찾기 등록 */
    @PostMapping("/bookmarks")
    public ResponseEntity<Void> createBookmark(
            @RequestBody @Valid BookmarkRequest bookmarkRequest
    ) {
        Long bookmarkNum = bookmarkApplicationService.createBookmark(bookmarkRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/bookmarks/" + bookmarkNum))
                .build();
    }

    /* 카테고리 삭제 */
    @DeleteMapping("/bookmarks/{bookmarkNum}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable final Long bookmarkNum) {

        bookmarkApplicationService.deleteBookmark(bookmarkNum);

        return ResponseEntity.noContent().build();
    }
}
