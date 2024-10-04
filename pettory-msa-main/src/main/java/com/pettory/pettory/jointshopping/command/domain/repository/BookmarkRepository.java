package com.pettory.pettory.jointshopping.command.domain.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;

public interface BookmarkRepository {
    Bookmark save(Bookmark newBookmark);

    void deleteById(Long bookmarkNum);
}
