package com.pettory.mainserver.counseling.question.command.application.service;

import com.pettory.mainserver.counseling.question.command.application.dto.QuestionCommandDTO;
import com.pettory.mainserver.counseling.question.command.domain.aggregate.Question;
import com.pettory.mainserver.counseling.question.command.domain.aggregate.QuestionFile;
import com.pettory.mainserver.counseling.question.command.domain.aggregate.QuestionState;
import com.pettory.mainserver.counseling.question.command.infrastructure.repository.QuestionFileRepository;
import com.pettory.mainserver.counseling.question.command.infrastructure.repository.QuestionRepository;
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

    @Transactional
    public void selectQuestion(QuestionCommandDTO questionCommandDTO) {
        Question question = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        question.increaseCounselingQuestionHits(question.getCounselingQuestionHits() + 1);
    }

    // 질문 작성 및 파일 저장
    @Transactional
    public void registQuestionWithFile(QuestionCommandDTO questionCommandDTO, MultipartFile file) throws IOException {
        // 1. 질문 저장
        Question question = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));

        // 2. 파일 저장 처리
        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/";
            String originalFilename = file.getOriginalFilename();
            String storageFileName = System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + storageFileName;

            // 파일 저장 디렉토리에 파일 저장
            File dest = new File(filePath);
            file.transferTo(dest);

            // 파일 메타데이터 저장
            QuestionFile questionFile = new QuestionFile(question.getCounselingQuestionNum(), (int) file.getSize(), filePath);
            questionFileRepository.save(questionFile);
        }
    }

    @Transactional
    public void modifyQuestion(QuestionCommandDTO questionCommandDTO) {
        Question foundQuestion = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        foundQuestion.modifyCounselingQuestion(questionCommandDTO.getCounselingQuestionTitle(), questionCommandDTO.getCounselingQuestionContent(), LocalDateTime.now().toString());
    }

    @Transactional
    public void deleteQuestion(QuestionCommandDTO questionCommandDTO) {
        Question foundQuestion = questionRepository.save(modelMapper.map(questionCommandDTO, Question.class));
        foundQuestion.deleteCounselingQuestion(QuestionState.DELETE, LocalDateTime.now().toString());
    }

}