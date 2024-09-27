package com.pettory.pettory.user.command.domain.aggregate;

import com.pettory.pettory.exception.AlreadyInFamilyException;
//import com.pettory.pettory.family.command.domain.aggregate.Family;
//import com.pettory.pettory.pet.command.domain.aggregate.Pet;
//import com.pettory.pettory.pet.command.domain.aggregate.PetAccess;
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

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)    // 엔터티 삽입, 수정 시간 기록 위함
@SQLDelete(sql = "UPDATE user SET user_state = 'W' where user_id = ?")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long userId;    // 회원id
    private String userEmail;   // 회원이메일
    private String userPassword;    // 회원비밀번호
    private String userNickname;    // 회원닉네임
    private String userName;    // 회원이름
    private LocalDate userBirth; // 회원생년월일

    // DB의 기본값 설정이 잘 동작하지 않는 경우가 발생하여 JPA로 기본값을 명시적으로 처리
    @Enumerated(EnumType.STRING)
    private UserState userState = UserState.ACTIVATED; // 회원상태  // 기본값 설정
    //    private boolean userVetYn = false;  // 회원수의사여부  // 기본값 설정
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;  // 회원역할
    private boolean userWalkingRecordPublicYn = true;  // 회원산책기록공개여부    // 기본값 설정
    private String userHospitalName;    // 회원병원이름
    private String userHospitalInfo;    // 회원병원정보
    @CreatedDate
    private LocalDateTime userRegisterDatetime; // 회원등록일시
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime userWithdrawDatetime; // 회원탈퇴일시
    private Long userSuspensionCount = 0L;   // 회원계정정지횟수
    private LocalDateTime userSuspensionEndDatetime; // 회원계정정지종료일시

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

}
