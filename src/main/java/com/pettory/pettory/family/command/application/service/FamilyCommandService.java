package com.pettory.pettory.family.command.application.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.exception.SelfException;
import com.pettory.pettory.exception.UnauthorizedException;
import com.pettory.pettory.family.command.domain.aggregate.Family;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FamilyCommandService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // 가족 구성원을 가족에서 삭제
    @Transactional
    public void kickFromFamily(String userEmail, Long memberId) {

        // 1. 로그인한 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 2. 삭제하는 회원 조회
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 3. 가족 조회
        Family family = owner.getFamily();
        if (family == null) {
            throw new NotFoundException("가족을 찾을 수 없습니다");
        }

        // 4. 삭제하는 회원이 가족의 주인인지 확인
        if (!family.isOwner(userId)) {
            throw new UnauthorizedException("가족의 주인만 가족구성원을 삭제할 수 있습니다.");
        }

        // 5. 삭제 당하는 회원 조회
        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("삭제하려는 회원을 찾을 수 없습니다."));

        // 6. 삭제 당하는 회원이 삭제하는 회원과 같은지 확인
        if (member.getUserId().equals(owner.getUserId())) {
            throw new SelfException("자기 자신을 삭제할 수 없습니다.");
        }

        // 7. 삭제 당하는 회원이 삭제하는 회원과 같은 가족인지 확인
        if (!member.getFamily().equals(owner.getFamily())) {
            throw new UnauthorizedException("해당 회원은 같은 가족이 아닙니다.");
        }

        // 8. 가족에서 회원 삭제
        member.updateFamilyIdAsNull();

        // 9. 회원을 삭제한 가족의 가족구성원이 1명 남은 경우 해당 가족 삭제
        if (family.getFamilyNumber() == 1) {
            family.updateFamilyStateAsDeleted();
        } else {
            // 가족 구성원 수 감소
            family.reduceFamilyNumber();
        }
    }
}
