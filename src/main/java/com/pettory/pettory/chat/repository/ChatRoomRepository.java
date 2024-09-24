package com.pettory.pettory.chat.repository;

import com.pettory.pettory.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {


}
