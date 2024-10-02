package com.pettory.pettory.user.query.dto;

import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfoResponse {

    private String userEmail;
    private String userNickname;
    private String userName;
    private LocalDate userBirth;

    private UserState userState;
    private UserRole userRole;
    private boolean userWalkingRecordPublicYn;
    private String userHospitalName;
    private String userHospitalInfo;
    private LocalDateTime userRegisterDatetime;
    private LocalDateTime userSuspensionEndDatetime;

}
