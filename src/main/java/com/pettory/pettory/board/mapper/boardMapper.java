package com.pettory.pettory.board.mapper;

import com.pettory.pettory.board.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface boardMapper {
    List<BoardEntity> selectBoardsByPostWriterNum(int currentUserId);


    List<BoardEntity> selectAllBoards();
    BoardEntity selectBoardById(int id);
}
