package com.pettory.mainserver.board.query.mapper;

import com.pettory.mainserver.board.query.dto.BoardPostDTO;
import com.pettory.mainserver.board.query.dto.BoardPostDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardPostMapper {

    List<BoardPostDTO> selectPosts();

    int countPosts();

    List<BoardPostDetailDTO> selectPostById(@Param("postNum") int postNum);

    List<String> postFilelinks(@Param("postNum") int postNum);
}
