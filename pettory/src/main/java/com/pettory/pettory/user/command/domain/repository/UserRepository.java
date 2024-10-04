package com.pettory.pettory.user.command.domain.repository;

import com.pettory.pettory.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


// domain - repository 는 jpa 기술에 의존하지 않는 순수 repository
public interface UserRepository {
    User save(User user);

    // 이메일로 회원을 찾는 메소드
    Optional<User> findByUserEmail(String userEmail);

    // 회원id로 회원을 찾는 메소드
    Optional<User> findById(Long invitationSendUserId);

    // 회원id로 회원의 가족id를 찾는 메소드
    @Query("SELECT u.family.familyId FROM User u WHERE u.userId = :userId")
    Optional<Long> findFamilyIdByUserId(@Param("userId") Long userId);

    Optional<User> findByUserNickname(String userNickname);

    boolean existsByUserEmail(String userEmail);

    boolean existsByUserNickname(String userNickname);
}
