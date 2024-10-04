package com.pettory.userserver.family.query.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@Builder
@NoArgsConstructor
public class JoinedFamilyResponse {
    private String familyName;          // 가족 이름
    private int familyNumber;           // 가족 구성원 수
    private List<String> familyMemberNames;  // 가족 구성원들의 이름 리스트
}
