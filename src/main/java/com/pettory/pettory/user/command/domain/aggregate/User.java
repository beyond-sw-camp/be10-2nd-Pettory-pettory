package com.pettory.pettory.user.command.domain.aggregate;

import com.pettory.pettory.exception.AlreadyInFamilyException;
import com.pettory.pettory.family.command.domain.aggregate.Family;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.aggregate.PetAccess;
import com.pettory.pettory.walkingRecord.command.domain.aggregate.WalkingRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)    // 엔터티 삽입, 수정 시간 기록 위함
@SQLDelete(sql = "UPDATE user SET user_state = 'DELETE', user_delete_datetime = NOW(), WHERE user_id = ? AND user_state != 'DELETE'")
@Schema(description = "회원DTO")
public class User {
    @Schema(description = "회원id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long userId;    // 회원id

    @Schema(description = "회원이메일")
    @Column(unique = true)
    private String userEmail;   // 회원이메일

    @Schema(description = "회원비밀번호")
    private String userPassword;    // 회원비밀번호

    @Schema(description = "회원닉네임")
    @Column(unique = true)
    private String userNickname;    // 회원닉네임

    @Schema(description = "회원이름")
    private String userName;    // 회원이름

    @Schema(description = "회원생년월일")
    private LocalDate userBirth; // 회원생년월일

    @Schema(description = "회원상태")
    // DB의 기본값 설정이 잘 동작하지 않는 경우가 발생하여 JPA로 기본값을 명시적으로 처리
    @Enumerated(EnumType.STRING)
    private UserState userState = UserState.ACTIVE; // 회원상태  // 기본값 설정

    @Schema(description = "회원역할")
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;  // 회원역할

    @Schema(description = "회원산책기록공개여부")
    private boolean userWalkingRecordPublicYn = true;  // 회원산책기록공개여부    // 기본값 설정

    private String userHospitalName;    // 회원병원이름
    private String userHospitalInfo;    // 회원병원정보

    @Schema(description = "회원등록일시")
    @CreatedDate
    private LocalDateTime userRegisterDatetime; // 회원등록일시

    @Schema(description = "회원수정일시")
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime userWithdrawDatetime; // 회원탈퇴일시

    @Schema(description = "회원정지횟수")
    private Long userSuspensionCount = 0L;   // 회원계정정지횟수

    private LocalDateTime userSuspensionEndDatetime; // 회원계정정지종료일시

    @Schema(description = "회원권한제공자")
    private String user_auth_provider = "LOCAL";

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @OneToMany(mappedBy = "user")
    List<Pet> pets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<PetAccess> petAccesses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<WalkingRecord> walkingRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<FeedingRecord> feedingRecords = new ArrayList<>();

    // 생성자는 private으로 설정하여 외부에서의 호출 방지
    private User(String userEmail, String userPassword, String userNickname, String userName, LocalDate userBirth) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userBirth = userBirth;
    }

    public static User create(String userEmail, String userPassword, String userNickname, String userName, LocalDate userBirth) {
        return new User(userEmail, userPassword, userNickname, userName, userBirth);
    }

    public void encryptPassword(String encodedPwd) {
        this.userPassword = encodedPwd;
    }

    // 회원을 가족에 포함시키는 메소드
    public void joinFamily(Family family) {
        if (this.family != null) {
            throw new AlreadyInFamilyException("이미 가족에 속해 있습니다.");
        }
        this.family = family;
        family.addFamilyMember();
    }

    // 회원의 가족id를 삭제하는 메소드
    public void updateFamilyIdAsNull() {
        this.family = null;
    }

    // 회원을 삭제하는 메소드
    public void updateUserAsDelete() {
        this.userState = UserState.DELETE;
    }

    // 회원의 비밀번호를 수정하는 메소드
    public void updatePassword(String encodedUserPassword) {
        this.userPassword = encodedUserPassword;
    }


}
