package com.pettory.pettory.board.command.infrastructure.repository;

import com.pettory.pettory.board.command.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPACategoryRepository extends JpaRepository<Category, Long> {
}
