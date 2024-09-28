package com.pettory.pettory.family.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.family.query.dto.JoinedFamilyResponse;
import com.pettory.pettory.family.query.mapper.FamilyMapper;
import com.pettory.pettory.security.util.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamilyQueryService {

    private final FamilyMapper familyMapper;

    // 회원이 속한 가족 조회
    @Transactional(readOnly = true)
    public JoinedFamilyResponse getFamilyInfo(String userEmail) {

        UserSecurity.validateCurrentUser(userEmail);

        return familyMapper.selectJoinedFamilyInfo(userEmail);
    }
}
