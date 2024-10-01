package com.pettory.pettory.board.repository;

import com.pettory.pettory.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer> {
}
