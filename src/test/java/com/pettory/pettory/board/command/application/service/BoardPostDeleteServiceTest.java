package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.domain.aggregate.Post;
import com.pettory.pettory.board.command.domain.service.BoardPostDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPAPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardPostDeleteServiceTest {

    @Mock
    private JPAPostRepository postRepository;

    @Mock
    private BoardPostDomainService boardPostDomainService;

    @InjectMocks
    private BoardPostDeleteService boardPostDeleteService;

    private Post post;

    @BeforeEach
    void setUp() {
        // 초기화 및 모의 데이터 설정
        post = new Post("Test Title", "Test Content", 1, 1);
    }

    @Test
    void testDeletePost() {
        // 게시글 존재 시의 동작을 테스트
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        // 삭제 메소드 호출
        boardPostDeleteService.deletePost(1);

        // 게시글이 삭제되었는지 확인 (실제로는 삭제 플래그가 변경됨)
        verify(boardPostDomainService, times(1)).deletePost(post);
        verify(postRepository, times(1)).save(post);

        // 출력문
        System.out.println("게시글 삭제 요청 처리 완료: " + post);
    }

    @Test
    void testDeletePostNotFound() {
        // 게시글이 존재하지 않을 때 예외를 던짐
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // 예외가 발생하는지 테스트
        assertThrows(IllegalArgumentException.class, () -> boardPostDeleteService.deletePost(1));

        // 출력문
        System.out.println("해당 게시글이 존재하지 않음: 삭제 실패");
    }
}
