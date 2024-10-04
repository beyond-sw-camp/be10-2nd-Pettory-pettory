package com.pettory.userserver.family.command.infrastructure.repository;

import com.pettory.userserver.family.command.domain.aggregate.Invitation;
import com.pettory.userserver.family.command.domain.repository.InvitationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAInvitationRepository  extends JpaRepository<Invitation, Long>, InvitationRepository {
}
