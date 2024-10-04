package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import com.pettory.pettory.board.query.mapper.BoardCategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardCategoryListServiceTest {

    @Mock
    private BoardCategoryMapper boardCategoryMapper;

    @InjectMocks
    private BoardCategoryListService boardCategoryListService;

    private List<BoardCategoryDTO> categoryList;

    @BeforeEach
    void setUp() {
        // 카테고리 데이터를 설정해둠
        categoryList = Arrays.asList(
                new BoardCategoryDTO(1, "Category1"),
                new BoardCategoryDTO(2, "Category2")
        );
    }

    @Test
    void testGetCategoryList_Success() {
        // 가짜 데이터 반환 설정
        when(boardCategoryMapper.selectCategoryList()).thenReturn(categoryList);

        // 서비스 호출
        List<BoardCategoryDTO> result = boardCategoryListService.getCategoryList();

        // 예상되는 결과와 비교
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getCategoryTitle());

        // 결과 출력
        System.out.println("카테고리 목록: ");
        result.forEach(category -> System.out.println("ID: " + category.getCategoryNum() + ", Name: " + category.getCategoryTitle()));

        // 카테고리 조회 메서드가 제대로 호출되었는지 확인
        verify(boardCategoryMapper, times(1)).selectCategoryList();
    }

    @Test
    void testGetCategoryList_EmptyList() {
        // 빈 리스트 반환 설정
        when(boardCategoryMapper.selectCategoryList()).thenReturn(Arrays.asList());

        // 서비스 호출
        List<BoardCategoryDTO> result = boardCategoryListService.getCategoryList();

        // 예상되는 결과와 비교
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // 결과 출력
        System.out.println("카테고리 목록이 없습니다.");

        // 카테고리 조회 메서드가 제대로 호출되었는지 확인
        verify(boardCategoryMapper, times(1)).selectCategoryList();
    }

    @Test
    void testGetCategoryList_ExceptionHandling() {
        // 예외 발생 시 상황을 가정
        when(boardCategoryMapper.selectCategoryList()).thenThrow(new RuntimeException("DB Error"));

        // 예외 발생 여부 확인
        Exception exception = assertThrows(RuntimeException.class, () -> {
            boardCategoryListService.getCategoryList();
        });

        // 예외 메시지 출력
        System.out.println("예외 발생: " + exception.getMessage());

        assertEquals("DB Error", exception.getMessage());
    }
}
