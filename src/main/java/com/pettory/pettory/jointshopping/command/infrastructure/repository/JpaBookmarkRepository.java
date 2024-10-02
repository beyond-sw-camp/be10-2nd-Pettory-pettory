package com.pettory.pettory.jointshopping.command.infrastructure.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.repository.BookmarkRepository;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookmarkRepository extends BookmarkRepository, JpaRepository<Bookmark, Long> {
}
