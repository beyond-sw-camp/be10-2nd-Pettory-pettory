package com.pettory.pettory.family.command.domain.aggregate;

import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE family SET family_state = 'DELETE', family_delete_datetime = NOW() where family_id = ? AND family_state != 'DELETE'")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;  // 가족id
    private String familyName;  // 가족이름

    @OneToMany(mappedBy = "family")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "family")
    private List<Pet> pets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FamilyState familyState = FamilyState.ACTIVE;    // 가족상태

    @CreatedDate
    private LocalDateTime familyInsertDatetime; // 가족등록일시
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime familyUpdateDatetime; // 가족수정일시
    private LocalDateTime familyDeleteDatetime; // 가족삭제일시
    private Long familyNumber = 0L;  // 가족구성원수
    private Long familyOwnerId = 0L;

    public Family(String familyName, FamilyState familyState, Long familyOwnerId) {

        this.familyName = familyName;
        this.familyState = familyState;
        this.familyOwnerId = familyOwnerId;
    }

    public static Family createFamily(String familyName, FamilyState familyState, Long familyOwnerId) {
        return new Family(familyName, familyState, familyOwnerId);
    }

    // 가족에 회원 수를 추가하는 메소드
    public void addFamilyMember() {
        if (this.familyNumber == null) {
            this.familyNumber = 0L;
        }
        this.familyNumber++;
    }

    // 가족에서 회원 수를 감소시키는 메소드
    public void reduceFamilyNumber() {
        this.familyNumber--;
    }

    // 가족 상태를 DELETE로 변경하는 메소드
    public void updateFamilyStateAsDeleted() {
        this.familyState = FamilyState.DELETE;
    }

    // 해당 회원이 가족의 주인인지 확인하는 메소드
    public boolean isOwner(Long userId) {
        return this.familyOwnerId.equals(userId);
    }

}
