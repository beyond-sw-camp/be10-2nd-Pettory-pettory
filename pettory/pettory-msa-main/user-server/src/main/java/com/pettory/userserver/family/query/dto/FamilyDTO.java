package com.pettory.userserver.family.query.dto;

import com.pettory.userserver.family.command.domain.aggregate.FamilyState;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FamilyDTO {

    private Long familyId;  // 가족id
    private String familyName;  // 가족이름
    private FamilyState familyState = FamilyState.ACTIVE;    // 가족상태
    private LocalDateTime familyInsertDatetime; // 가족등록일시
    private LocalDateTime familyUpdateDatetime; // 가족수정일시
    private LocalDateTime familyDeleteDatetime; // 가족삭제일시
    private Long familyNumber;  // 가족구성원수   // 추가
    private Long familyOwnerId; // 가족주인id   // 추가
}
