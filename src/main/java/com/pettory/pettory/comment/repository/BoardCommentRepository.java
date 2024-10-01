package com.pettory.pettory.comment.repository;

import com.pettory.pettory.comment.entity.BoardCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Integer> {
    List<BoardCommentEntity> findByPost_PostNum(int postNum);
    List<BoardCommentEntity> findByRecommentNum_commentNum(int recommentNum);
}
