package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.domain.aggregate.FileEntity;
import com.pettory.pettory.board.command.infrastructure.repository.JPAFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final JPAFileRepository jpaFileRepository;

    @Transactional
    public void saveFiles(List<MultipartFile> files, Long postNum) throws IOException {
        for (MultipartFile file : files) {
            // 파일 저장 경로 설정 (예: 로컬 디렉터리)
            ClassPathResource resource = new ClassPathResource("files/");
//            String uploadDir = resource.getFile().getAbsolutePath();
            String uploadDir = System.getProperty("user.dir") + "/uploaded-files/";

            String filePath = uploadDir + "/" + file.getOriginalFilename();

            // 파일을 서버에 저장
            File dest = new File(filePath);
            file.transferTo(dest);

            // 파일 정보 저장
            FileEntity fileEntity = new FileEntity(  // FileEntity로 변경
                    filePath,
                    postNum,
                    file.getSize()
            );
            jpaFileRepository.save(fileEntity);  // 파일 정보 DB에 저장
        }
    }
    // 게시글 번호를 기반으로 파일 링크를 가져오는 메서드 추가
    public List<String> getFileLinksByPostId(Long postNum) {
        return jpaFileRepository.findFileLinksByPostNum(postNum);
    }


}