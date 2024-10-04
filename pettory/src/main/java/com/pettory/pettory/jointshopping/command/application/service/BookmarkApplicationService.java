package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.BookmarkRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.service.BookmarkDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkApplicationService {

    private final BookmarkDomainService bookmarkDomainService;

    /* 즐겨찾기 등록 */
    @Transactional
    public Bookmark createBookmark(BookmarkRequest bookmarkRequest) {

        /* Bookmark 도메인 로직 실행, entity 반환 */
        Bookmark newBookmark = bookmarkDomainService.createBookmark(bookmarkRequest);

        /* save 로직 실행 */
        Bookmark bookmark = bookmarkDomainService.saveBookmark(newBookmark);

        /* 엔티티 반환 */
        return bookmark;

    }

    /* 즐겨찾기 삭제 */
    @Transactional
    public void deleteBookmark(Long bookmarkNum) {
        bookmarkDomainService.deleteBookmark(bookmarkNum);
    }
}
