package com.pettory.mainserver.board.command.infrastructure.repository;

import com.pettory.mainserver.board.command.domain.aggregate.Post;
import com.pettory.mainserver.board.query.dto.BoardPostDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAPostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Query("UPDATE Post p SET p.postHits = p.postHits + 1 WHERE p.postNum = :postNum")
    void incrementPostHits(@Param("postNum") Long postNum);

    @Query("SELECT new com.pettory.mainserver.board.query.dto.BoardPostDetailDTO(p.postNum, p.postTitle, p.postContent, p.postHits, p.postWriterNum, p.postInsertDatetime) " +
            "FROM Post p WHERE p.postNum = :postNum")
    List<BoardPostDetailDTO> findPostDetailsByPostNum(int postNum);


}