package com.pettory.mainserver.counseling.answer.command.infrastructure.repository;

import com.pettory.mainserver.counseling.answer.command.domain.aggregate.AnswerFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerFileRepository extends JpaRepository<AnswerFile, Integer> {
}