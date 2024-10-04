package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardCommentDTO;
import com.pettory.pettory.board.query.dto.BoardCommentResponse;
import com.pettory.pettory.board.query.mapper.BoardCommentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardCommentListServiceTest {

    @Mock
    private BoardCommentMapper boardCommentMapper;

    @InjectMocks
    private BoardCommentListService boardCommentListService;

    private BoardCommentDTO commentDTO;
    private List<BoardCommentDTO> commentList;

    @BeforeEach
    void setUp() {
        // 댓글 데이터를 설정해둠
        commentDTO = new BoardCommentDTO(
                1, "Test Comment", LocalDateTime.now(),1, 0
        );
        commentList = Arrays.asList(commentDTO);
    }

    @Test
    void testGetCommentList_Success() {
        // 가짜 데이터 반환 설정
        when(boardCommentMapper.selectComments(anyInt())).thenReturn(commentList);
        when(boardCommentMapper.countComments(anyInt())).thenReturn(1);

        // 서비스 호출
        BoardCommentResponse response = boardCommentListService.getCommentList(1);

        // 예상되는 결과와 비교
        assertNotNull(response);
        assertEquals(commentList, response.getComments());
        assertEquals(1, response.getTotalComments());

        // 결과 출력
        System.out.println("댓글 목록: " + response.getComments());
        System.out.println("총 댓글 수: " + response.getTotalComments());

        // 댓글 조회 메서드가 제대로 호출되었는지 확인
        verify(boardCommentMapper, times(1)).selectComments(anyInt());
        verify(boardCommentMapper, times(1)).countComments(anyInt());
    }

    @Test
    void testGetCommentList_NoCommentsFound() {
        // 댓글이 없을 경우 빈 리스트 반환 설정
        when(boardCommentMapper.selectComments(anyInt())).thenReturn(Arrays.asList());
        when(boardCommentMapper.countComments(anyInt())).thenReturn(0);

        // 서비스 호출
        BoardCommentResponse response = boardCommentListService.getCommentList(1);

        // 예상되는 결과와 비교
        assertNotNull(response);
        assertTrue(response.getComments().isEmpty());
        assertEquals(0, response.getTotalComments());

        // 결과 출력
        System.out.println("댓글 목록: " + response.getComments());
        System.out.println("총 댓글 수: " + response.getTotalComments());

        // 댓글 조회 메서드가 제대로 호출되었는지 확인
        verify(boardCommentMapper, times(1)).selectComments(anyInt());
        verify(boardCommentMapper, times(1)).countComments(anyInt());
    }

    @Test
    void testGetCommentList_ExceptionHandling() {
        // 데이터베이스 오류 발생 시 상황을 가정
        when(boardCommentMapper.selectComments(anyInt())).thenThrow(new RuntimeException("DB Error"));

        // 예외 발생 여부 확인
        Exception exception = assertThrows(RuntimeException.class, () -> {
            boardCommentListService.getCommentList(1);
        });

        // 예외 메시지 출력
        System.out.println("예외 발생: " + exception.getMessage());

        assertEquals("DB Error", exception.getMessage());
    }
}
