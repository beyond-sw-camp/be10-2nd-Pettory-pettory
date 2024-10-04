package com.pettory.userserver.user.command.infrastructure.repository;

import com.pettory.userserver.user.command.domain.aggregate.User;
import com.pettory.userserver.user.command.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}