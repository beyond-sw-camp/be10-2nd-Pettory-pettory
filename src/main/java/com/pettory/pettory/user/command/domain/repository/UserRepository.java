package com.pettory.pettory.user.command.domain.repository;

import com.pettory.pettory.user.command.domain.aggregate.User;

import java.util.Optional;


// domain - repository 는 jpa 기술에 의존하지 않는 순수 repository
public interface UserRepository {
    User save(User user);

    // 이메일로 회원을 찾는 메소드
    Optional<User> findByUserEmail(String userEmail);

}
