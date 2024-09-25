package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.jointshopping.command.application.dto.BookmarkRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkDomainService {

    private final BookmarkRepository bookmarkRepository;
    private final ModelMapper modelMapper;

    /* 도메인 객체를 생성하는 로직 */
    public Bookmark createBookmark(BookmarkRequest bookmarkRequest) {

        /* entity 생성 */
        Bookmark newBookmark = modelMapper.map(bookmarkRequest, Bookmark.class);

        return newBookmark;
    }

    /* 도메인 객체를 저장하는 로직 */
    public Bookmark saveBookmark(Bookmark newBookmark) {
        return bookmarkRepository.save(newBookmark);
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteBookmark(Long bookmarkNum) {
        bookmarkRepository.deleteById(bookmarkNum);
    }
}
