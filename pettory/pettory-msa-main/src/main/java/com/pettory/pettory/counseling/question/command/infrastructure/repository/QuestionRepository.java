package com.pettory.pettory.counseling.question.command.infrastructure.repository;

import com.pettory.pettory.counseling.question.command.domain.aggregate.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}