package com.pettory.pettory.counseling.question.command.application.service;

import com.pettory.pettory.counseling.question.command.application.dto.QuestionCommandDTO;
import com.pettory.pettory.counseling.question.command.domain.aggregate.Question;
import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionFile;
import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionState;
import com.pettory.pettory.counseling.question.command.infrastructure.repository.QuestionFileRepository;
import com.pettory.pettory.counseling.question.command.infrastructure.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class QuestionCommandService {

    private final QuestionRepository questionRepository;
    private final QuestionFileRepository questionFileRepository;
    private final ModelMapper modelMapper;

    public QuestionCommandService(QuestionRepository questionRepository, QuestionFileRepository questionFileRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.questionFileRepository = questionFileRepository;
        this.modelMapper = modelMapper;
    }

    // 질문 작성 및 파일 저장
    @Transactional
    public int createQuestion(QuestionCommandDTO questionCommandDTO, MultipartFile questionCommandImg) throws IOException {
        // 1. 질문 저장
        Question question = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));

        // 2. 파일 저장 처리
        if (questionCommandImg != null && !questionCommandImg.isEmpty()) {
            String uploadDir = "uploads/";
            String originalFilename = questionCommandImg.getOriginalFilename();
            String storageFileName = System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + storageFileName;

            // 파일 저장 디렉토리에 파일 저장
            File dest = new File(filePath);
            questionCommandImg.transferTo(dest);

            // 파일 메타데이터 저장
            QuestionFile questionFile = new QuestionFile(question.getCounselingQuestionNum(), (int) questionCommandImg.getSize(), filePath);
            questionFileRepository.save(questionFile);
        }

        return question.getCounselingQuestionNum();
    }

    @Transactional
    public void modifyQuestion(QuestionCommandDTO questionCommandDTO) {
        Question foundQuestion = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        foundQuestion.modifyCounselingQuestion(questionCommandDTO.getCounselingQuestionTitle(), questionCommandDTO.getCounselingQuestionContent(), LocalDateTime.now().toString());
    }

    @Transactional
    public void increaseHits(QuestionCommandDTO questionCommandDTO) {
        Question question = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        question.increaseCounselingQuestionHits(question.getCounselingQuestionHits() + 1);
    }

    @Transactional
    public void removeQuestion(QuestionCommandDTO questionCommandDTO) {
        Question foundQuestion = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        foundQuestion.removeCounselingQuestion(QuestionState.DELETE, LocalDateTime.now().toString());
    }

}