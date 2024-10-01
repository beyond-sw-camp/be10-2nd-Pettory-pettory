package com.pettory.pettory.board.query.mapper;

import com.pettory.pettory.board.query.dto.BoardPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardPostMapper {

    List<BoardPostDTO> selectPosts(@Param("postTitle") String postTitle);

    int countPosts(@Param("postTitle") String postTitle);
}
