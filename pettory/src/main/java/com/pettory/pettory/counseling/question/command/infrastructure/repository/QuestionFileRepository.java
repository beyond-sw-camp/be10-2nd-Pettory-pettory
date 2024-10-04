package com.pettory.pettory.counseling.question.command.infrastructure.repository;

import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionFileRepository extends JpaRepository<QuestionFile, Integer> {
}