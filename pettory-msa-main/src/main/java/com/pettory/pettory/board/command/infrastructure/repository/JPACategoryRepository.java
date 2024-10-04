package com.pettory.pettory.board.command.infrastructure.repository;

import com.pettory.pettory.board.command.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACategoryRepository extends JpaRepository<Category, Long> {
}
