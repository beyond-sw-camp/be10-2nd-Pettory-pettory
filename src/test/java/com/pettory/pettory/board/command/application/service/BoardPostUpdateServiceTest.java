package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.application.dto.PostUpdateRequest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardPostUpdateServiceTest {

    @Mock
    private JPAPostRepository postRepository;

    @Mock
    private BoardPostDomainService boardPostDomainService;

    @InjectMocks
    private BoardPostUpdateService boardPostUpdateService;

    private Post post;
    private PostUpdateRequest postUpdateRequest;

    @BeforeEach
    void setUp() {
        // 초기화 및 모의 데이터 설정
        post = new Post("Original Title", "Original Content", 1, 1);
        postUpdateRequest = new PostUpdateRequest("Updated Title", "Updated Content", 1);

        // 수정 테스트를 위해 findById에 대해 post를 반환하도록 설정
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
    }

    @Test
    void testUpdatePost() {
        // 게시글 수정 요청
        PostUpdateRequest updateRequest = new PostUpdateRequest("Updated Title", "Updated Content", 1);

        // 수정 메서드 호출
        boardPostUpdateService.updatePost(1, updateRequest);

        // 수정된 게시글이 저장된 것을 검증
        verify(postRepository, times(1)).save(post);

        // 결과 출력 및 검증
        System.out.println("게시글 수정 완료:");
        System.out.println("제목: " + post.getPostTitle());
        System.out.println("내용: " + post.getPostContent());
        System.out.println("업데이트 시간: " + post.getPostUpdateDatetime());

        assertEquals("Updated Title", post.getPostTitle());
        assertEquals("Updated Content", post.getPostContent());
        assertNotNull(post.getPostUpdateDatetime());
    }

    @Test
    void testUpdatePostNotFound() {
        // 게시글이 존재하지 않는 경우 예외 발생
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        // 예외 발생 확인
        assertThrows(IllegalArgumentException.class, () -> boardPostUpdateService.updatePost(1, postUpdateRequest));

        // 출력문
        System.out.println("해당 게시글이 존재하지 않음: 수정 실패");
    }
}