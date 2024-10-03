package com.pettory.pettory.board.query.mapper;

import com.pettory.pettory.board.query.dto.BoardPostDTO;
import com.pettory.pettory.board.query.dto.BoardPostDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardPostMapper {

    List<BoardPostDTO> selectPosts();

    int countPosts();

    List<BoardPostDetailDTO> selectPostById(@Param("postId") int postId);

    List<String> postFilelinks(@Param("postId") int postId);
}
