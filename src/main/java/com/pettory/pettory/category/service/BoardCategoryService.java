package com.pettory.pettory.category.service;

import com.pettory.pettory.category.entity.BoardCategoryEntity;
import com.pettory.pettory.category.repository.BoardCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    @Autowired
    public BoardCategoryService(BoardCategoryRepository boardCategoryRepository) {
        this.boardCategoryRepository = boardCategoryRepository;
    }

    public BoardCategoryEntity createCategory(String title) {
        BoardCategoryEntity category = new BoardCategoryEntity();
        category.setCategoryTitle(title);
        return boardCategoryRepository.save(category);
    }

    public BoardCategoryEntity updateCategory(int categoryNum, String newTitle) {
        BoardCategoryEntity category = boardCategoryRepository.findById(categoryNum)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        category.setCategoryTitle(newTitle);
        return boardCategoryRepository.save(category);
    }

    public void deleteCategory(int categoryNum) {
        boardCategoryRepository.deleteById(categoryNum);
    }
}

