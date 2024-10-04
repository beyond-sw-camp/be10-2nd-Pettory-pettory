package com.pettory.mainserver.board.command.infrastructure.repository;

import com.pettory.mainserver.board.command.domain.aggregate.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACommentRepository extends JpaRepository<Comment, Long> {
}
