package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardPostDTO;
import com.pettory.pettory.board.query.dto.BoardPostListResponse;
import com.pettory.pettory.board.query.mapper.BoardPostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardPostListServiceTest {

    @Mock
    private BoardPostMapper boardPostMapper;

    @InjectMocks
    private BoardPostListService boardPostListService;

    @BeforeEach
    void setup() {
        // 테스트 데이터 설정
        BoardPostDTO post1 = new BoardPostDTO(1, "Post 1", 10, LocalDateTime.now());
        BoardPostDTO post2 = new BoardPostDTO(2, "Post 2", 20, LocalDateTime.now());

        List<BoardPostDTO> postList = Arrays.asList(post1, post2);

        // Mock 설정: 게시글 목록 반환
        when(boardPostMapper.selectPosts()).thenReturn(postList);

        // Mock 설정: 총 게시글 수 반환
        when(boardPostMapper.countPosts()).thenReturn(postList.size());
    }

    @Test
    void testGetPostList() {
        // 게시글 목록과 총 게시글 수 조회
        BoardPostListResponse response = boardPostListService.getPostList();

        // 실제 조회된 게시글 출력
        System.out.println("조회된 게시글 목록:");
        response.getPostList().forEach(post ->
                System.out.println("게시글 번호: " + post.getPostNum() +
                        ", 제목: " + post.getPostTitle() +
                        ", 조회수: " + post.getPostHits() +
                        ", 작성일시: " + post.getPostInsertDatetime())
        );

        // 테스트 검증: 총 게시글 수 확인
        assertEquals(2, response.getTotalPosts(), "총 게시글 수가 일치해야 합니다.");
        assertEquals(2, response.getPostList().size(), "조회된 게시글 목록의 크기가 일치해야 합니다.");
    }
}
