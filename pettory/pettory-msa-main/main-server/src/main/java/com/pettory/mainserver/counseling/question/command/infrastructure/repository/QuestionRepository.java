package com.pettory.mainserver.counseling.question.command.infrastructure.repository;

import com.pettory.mainserver.counseling.question.command.domain.aggregate.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}