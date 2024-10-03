package com.pettory.pettory.board.query.mapper;

import com.pettory.pettory.board.query.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface BoardCategoryMapper {

    List<BoardCategoryDTO> selectCategoryList();
}
