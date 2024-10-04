package com.pettory.userserver.family.query.service;

import com.pettory.userserver.exception.EmptyResultException;
import com.pettory.userserver.family.query.dto.JoinedFamilyResponse;
import com.pettory.userserver.family.query.mapper.FamilyMapper;
import com.pettory.userserver.security.util.UserSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyQueryService {

    private final FamilyMapper familyMapper;

    // 회원이 속한 가족 조회
    @Transactional(readOnly = true)
    public JoinedFamilyResponse getFamilyInfo(String userEmail) {

        UserSecurity.validateCurrentUser(userEmail);
        log.info("getFamilyInfo - userEmail: " + userEmail);

        // 가족 정보 조회
        JoinedFamilyResponse familyInfo = familyMapper.selectJoinedFamilyInfo(userEmail);

        if (familyInfo == null) {
            throw new EmptyResultException("가족 정보가 없습니다.");
        }
        return familyInfo;
    }
}
