package com.pettory.pettory.category.service;

import com.pettory.pettory.category.entity.BoardCategoryEntity;
import com.pettory.pettory.category.repository.BoardCategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BoardCategoryServiceTest {

    @Mock
    private BoardCategoryRepository boardCategoryRepository;

    @InjectMocks
    private BoardCategoryService boardCategoryService;

    public BoardCategoryServiceTest() {
        MockitoAnnotations.openMocks(this);  // Mockito 초기화
    }

    @Test
    public void testCreateCategory() {
        String categoryTitle = "Test Category";

        BoardCategoryEntity categoryEntity = new BoardCategoryEntity();
        categoryEntity.setCategoryTitle(categoryTitle);

        when(boardCategoryRepository.save(any(BoardCategoryEntity.class))).thenReturn(categoryEntity);

        BoardCategoryEntity result = boardCategoryService.createCategory(categoryTitle);

        assertNotNull(result);
        assertEquals(categoryTitle, result.getCategoryTitle());
        verify(boardCategoryRepository, times(1)).save(any(BoardCategoryEntity.class));
    }

    @Test
    public void testUpdateCategory() {
        int categoryNum = 1;
        String newTitle = "Updated Category Title";

        BoardCategoryEntity existingCategory = new BoardCategoryEntity();
        existingCategory.setCategoryTitle("Old Category Title");

        when(boardCategoryRepository.findById(categoryNum)).thenReturn(Optional.of(existingCategory));
        when(boardCategoryRepository.save(existingCategory)).thenReturn(existingCategory);

        BoardCategoryEntity result = boardCategoryService.updateCategory(categoryNum, newTitle);

        assertNotNull(result);
        assertEquals(newTitle, result.getCategoryTitle());
        verify(boardCategoryRepository, times(1)).save(existingCategory);
    }

    @Test
    public void testDeleteCategory() {
        int categoryNum = 1;

        doNothing().when(boardCategoryRepository).deleteById(categoryNum);

        boardCategoryService.deleteCategory(categoryNum);

        verify(boardCategoryRepository, times(1)).deleteById(categoryNum);
    }
}
