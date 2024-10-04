package com.pettory.pettory.chat.repository;

import com.pettory.pettory.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/* 채팅방 Repository 생성 - extends JpaRepository 한 상황으로 기본적인 CRUD 가능 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {


}
