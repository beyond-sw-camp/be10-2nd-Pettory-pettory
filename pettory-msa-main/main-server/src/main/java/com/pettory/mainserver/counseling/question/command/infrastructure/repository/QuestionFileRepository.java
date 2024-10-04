package com.pettory.mainserver.counseling.question.command.infrastructure.repository;

import com.pettory.mainserver.counseling.question.command.domain.aggregate.QuestionFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionFileRepository extends JpaRepository<QuestionFile, Integer> {
}