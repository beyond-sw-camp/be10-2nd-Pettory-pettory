package com.pettory.pettory.counseling.answer.command.infrastructure.repository;

import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerFileRepository extends JpaRepository<AnswerFile, Integer> {
}