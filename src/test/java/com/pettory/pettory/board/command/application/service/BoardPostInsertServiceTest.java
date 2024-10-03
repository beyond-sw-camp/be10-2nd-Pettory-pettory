package com.pettory.pettory.board.command.application.service;

import com.pettory.pettory.board.command.application.dto.PostRequest;
import com.pettory.pettory.board.command.domain.aggregate.Post;
import com.pettory.pettory.board.command.domain.service.BoardPostDomainService;
import com.pettory.pettory.board.command.infrastructure.repository.JPAPostRepository;
import com.pettory.pettory.board.query.dto.BoardPostDetailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)  // 이 부분을 추가합니다.
class BoardPostInsertServiceTest {

    @Mock
    private JPAPostRepository jpaPostRepository;

    @Mock
    private BoardPostDomainService boardPostDomainService;

    @InjectMocks
    private BoardPostInsertService boardPostInsertService;

    private PostRequest postRequest;
    private Post post;

    @BeforeEach
    void setUp() {
        // 초기화 및 모의 데이터 설정
        postRequest = new PostRequest("Test Title", "Test Content", 1, 1);
        post = new Post("Test Title", "Test Content", 1, 1);

        // lenient로 설정하여 엄격한 stubbing 검증을 완화
        lenient().when(boardPostDomainService.createPost(postRequest)).thenReturn(post);
        lenient().when(jpaPostRepository.save(post)).thenReturn(post);

        // JPAPostRepository의 findPostDetailsByPostNum 모킹 설정
        when(jpaPostRepository.findPostDetailsByPostNum(1)).thenReturn(
                Arrays.asList(new BoardPostDetailDTO(1, "Test Title", "Test Content", 0, 1, null))
        );
    }




    @Test
    void testCreatePost() {
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);

        // 게시글 생성 테스트
        boardPostInsertService.createPost(postRequest);

        // 결과 출력
        verify(jpaPostRepository).save(postCaptor.capture());
        Post savedPost = postCaptor.getValue();
        System.out.println("저장된 게시글: " + savedPost);

        // postNum이 제대로 생성되었는지 검증
        assertNotNull(savedPost);
        assertEquals("Test Title", savedPost.getPostTitle());
    }


    @Test
    void testGetPostDetails() {
        // 게시글 세부 정보 가져오기 테스트
        List<BoardPostDetailDTO> postDetails = boardPostInsertService.getPostDetails(1);

        // 결과 출력
        System.out.println("게시글 세부 정보: " + postDetails);

        // 검증
        assertNotNull(postDetails);
        assertEquals(1, postDetails.size());
        assertEquals("Test Title", postDetails.get(0).getPostTitle());

        // jpaPostRepository의 findPostDetailsByPostNum 호출 검증
        verify(jpaPostRepository, times(1)).findPostDetailsByPostNum(1);
    }
}
