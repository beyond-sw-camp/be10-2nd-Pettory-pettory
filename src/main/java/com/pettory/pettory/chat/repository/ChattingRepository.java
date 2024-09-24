package com.pettory.pettory.chat.repository;

import com.pettory.pettory.chat.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Integer> {

}
