package com.pettory.mainserver.chat.repository;

import com.pettory.mainserver.chat.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/* 투표 Repository 생성 - extends JpaRepository 한 상황으로 기본적인 CRUD 가능 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {

}
