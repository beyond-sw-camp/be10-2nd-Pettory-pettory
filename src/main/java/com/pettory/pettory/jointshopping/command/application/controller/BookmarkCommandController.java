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
    @PostMapping("/bookmark")
    public ResponseEntity<Void> createBookmark(
            @RequestBody @Valid BookmarkRequest bookmarkRequest
    ) {
        Long bookmarkNum = bookmarkApplicationService.createBookmark(bookmarkRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/bookmark/" + bookmarkNum))
                .build();
    }

    /* 카테고리 삭제 */
    @DeleteMapping("/bookmark/{bookmarkNum}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable final Long bookmarkNum) {

        bookmarkApplicationService.deleteCategory(bookmarkNum);

        return ResponseEntity.noContent().build();
    }
}
