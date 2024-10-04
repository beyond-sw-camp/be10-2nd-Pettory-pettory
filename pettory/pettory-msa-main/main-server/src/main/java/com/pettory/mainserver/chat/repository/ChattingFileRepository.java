package com.pettory.mainserver.chat.repository;

import com.pettory.mainserver.chat.entity.ChattingFile;
import org.springframework.data.jpa.repository.JpaRepository;

/* 채팅 파일 Repository 생성 - extends JpaRepository 한 상황으로 기본적인 CRUD 가능 */
public interface ChattingFileRepository extends JpaRepository<ChattingFile, Integer> {

}
