package com.pettory.pettory.counseling.answer.command.application.service;

import com.pettory.pettory.counseling.answer.command.application.dto.AnswerDTO;
import com.pettory.pettory.counseling.answer.command.domain.aggregate.Answer;
import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerFile;
import com.pettory.pettory.counseling.answer.command.infrastructure.repository.AnswerFileRepository;
import com.pettory.pettory.counseling.answer.command.infrastructure.repository.AnswerRepository;
import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerState;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerFileRepository answerFileRepository;
    private final ModelMapper modelMapper;

    public AnswerService(AnswerRepository answerRepository, AnswerFileRepository answerFileRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.answerFileRepository = answerFileRepository;
        this.modelMapper = modelMapper;
    }

    // 답변 작성 및 파일 저장
    @Transactional
    public int createAnswer(AnswerDTO answerDTO, MultipartFile answerImg) throws IOException {
        // 1. 답변 저장
        Answer answer = answerRepository.save(modelMapper.map(answerDTO, Answer.class));

        // 2. 파일 저장 처리
        if (answerImg != null && !answerImg.isEmpty()) {
            String uploadDir = "uploads/";
            String originalFilename = answerImg.getOriginalFilename();
            String storageFileName = System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + storageFileName;

            // 파일 저장 디렉토리에 파일 저장
            File dest = new File(filePath);
            answerImg.transferTo(dest);

            // 파일 메타데이터 저장
            AnswerFile answerFile = new AnswerFile(answer.getCounselingAnswerNum(), (int) answerImg.getSize(), filePath);
            answerFileRepository.save(answerFile);
        }

        return answer.getCounselingAnswerNum();
    }

    @Transactional
    public void modifyAnswer(AnswerDTO answerDTO) {
        Answer foundAnswer = answerRepository.save(modelMapper.map(answerDTO, Answer.class));
        foundAnswer.modifyCounselingAnswer(answerDTO.getCounselingAnswerContent(), LocalDateTime.now().toString());
    }

    @Transactional
    public void removeAnswer(AnswerDTO answerDTO) {
        Answer foundAnswer = answerRepository.save(modelMapper.map(answerDTO, Answer.class));
        foundAnswer.removeCounselingAnswer(AnswerState.DELETE, LocalDateTime.now().toString());
    }

    @Transactional
    public int createSubAnswer(AnswerDTO answerDTO, MultipartFile subAnswerImg) throws IOException {
        Answer subAnswer = answerRepository.save(modelMapper.map(answerDTO, Answer.class));

        // 재답변 저장
        subAnswer.createSubAnswer(subAnswer.getCounselingAnswerNum());

        if (subAnswerImg != null && !subAnswerImg.isEmpty()) {
            String uploadDir = "uploads/";
            String originalFilename = subAnswerImg.getOriginalFilename();
            String storageFileName = System.currentTimeMillis() + "_" + originalFilename;
            String filePath = uploadDir + storageFileName;

            File dest = new File(filePath);
            subAnswerImg.transferTo(dest);

            AnswerFile subAnswerFile = new AnswerFile(subAnswer.getCounselingAnswerReanswerNum(), (int) subAnswerImg.getSize(), filePath);
            answerFileRepository.save(subAnswerFile);
        }

        return subAnswer.getCounselingAnswerReanswerNum();
    }

}