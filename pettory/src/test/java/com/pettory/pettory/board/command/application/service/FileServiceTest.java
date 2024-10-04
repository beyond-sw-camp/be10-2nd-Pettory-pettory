package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.domain.aggregate.FileEntity;
import com.pettory.pettory.board.command.infrastructure.repository.JPAFileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @Mock
    private JPAFileRepository jpaFileRepository;

    @InjectMocks
    private FileService fileService;

    private MultipartFile mockFile;
    private Long postNum;

    @BeforeEach
    void setUp() throws IOException {
        // 모의 MultipartFile 설정
        mockFile = mock(MultipartFile.class);
        // 필요 없는 stubbing 제거
        postNum = 1L;
    }

    @Test
    void testSaveFiles() throws IOException {
        // 파일 저장 경로 설정 및 파일 저장 테스트
        List<MultipartFile> files = Arrays.asList(mockFile);

        // 파일 저장 로직을 모의 처리
        doAnswer(invocation -> {
            File destFile = invocation.getArgument(0);
            System.out.println("파일 저장 경로: " + destFile.getAbsolutePath());
            return null;
        }).when(mockFile).transferTo(any(File.class));

        // 테스트 호출
        fileService.saveFiles(files, postNum);

        // 파일 저장이 제대로 이루어졌는지 검증
        verify(mockFile, times(1)).transferTo(any(File.class));
        verify(jpaFileRepository, times(1)).save(any(FileEntity.class));

        System.out.println("파일이 성공적으로 저장되었습니다.");
    }

    @Test
    void testGetFileLinksByPostId() {
        // 모의 반환값 설정
        List<String> mockFileLinks = Arrays.asList("files/testfile1.txt", "files/testfile2.txt");
        when(jpaFileRepository.findFileLinksByPostNum(postNum)).thenReturn(mockFileLinks);

        // 테스트 호출
        List<String> fileLinks = fileService.getFileLinksByPostId(postNum);

        // 결과 확인
        assertTrue(fileLinks.size() > 0);

        System.out.println("파일 링크 목록:");
        fileLinks.forEach(System.out::println);

        // 메서드 호출 검증
        verify(jpaFileRepository, times(1)).findFileLinksByPostNum(postNum);
    }
}
