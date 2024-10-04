package com.pettory.pettory.board.query.service;

import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import com.pettory.pettory.board.query.mapper.BoardCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCategoryListService {

    private final BoardCategoryMapper boardCategoryMapper;

    // 카테고리 조회
    public List<BoardCategoryDTO> getCategoryList() {

        return boardCategoryMapper.selectCategoryList();
    }


}
