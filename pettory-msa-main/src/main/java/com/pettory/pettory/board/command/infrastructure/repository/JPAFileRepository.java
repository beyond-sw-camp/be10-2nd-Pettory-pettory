package com.pettory.pettory.board.command.infrastructure.repository;

import com.pettory.pettory.board.command.domain.aggregate.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JPAFileRepository extends JpaRepository<FileEntity, Long> {

    @Query("SELECT f.boardFIleStorageDirectory FROM FileEntity f WHERE f.postNum = :postNum")
    List<String> findFileLinksByPostNum(Long postNum);
}
