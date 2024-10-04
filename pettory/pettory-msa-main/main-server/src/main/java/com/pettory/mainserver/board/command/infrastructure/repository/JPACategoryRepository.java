package com.pettory.mainserver.board.command.infrastructure.repository;

import com.pettory.mainserver.board.command.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACategoryRepository extends JpaRepository<Category, Long> {
}
