package com.pettory.pettory.category.repository;

import com.pettory.pettory.category.entity.BoardCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategoryEntity, Integer> {

}

