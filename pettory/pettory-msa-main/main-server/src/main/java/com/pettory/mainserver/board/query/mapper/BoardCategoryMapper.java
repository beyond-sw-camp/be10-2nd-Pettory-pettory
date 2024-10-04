package com.pettory.mainserver.board.query.mapper;

import com.pettory.mainserver.board.query.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface BoardCategoryMapper {

    List<BoardCategoryDTO> selectCategoryList();
}
