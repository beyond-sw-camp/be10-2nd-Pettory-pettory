package com.pettory.mainserver.jointshopping.command.infrastructure.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.mainserver.jointshopping.command.domain.repository.BookmarkRepository;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookmarkRepository extends BookmarkRepository, JpaRepository<Bookmark, Long> {
}
