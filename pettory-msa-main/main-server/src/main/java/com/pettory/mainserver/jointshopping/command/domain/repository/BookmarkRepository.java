package com.pettory.mainserver.jointshopping.command.domain.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.Bookmark;

public interface BookmarkRepository {
    Bookmark save(Bookmark newBookmark);

    void deleteById(Long bookmarkNum);
}
