package com.pettory.pettory.user.query.dto;

import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userNickname;
    @NotBlank
    private String userName;
    @NotNull
    private LocalDate userBirth;
    @NotNull
    private UserState userState;
    @NotNull
    private UserRole userRole;
    @NotNull
    private boolean userWalkingRecordPublicYn;
    private String userHospitalName;
    private String userHospitalInfo;
    @NotNull
    private LocalDateTime userRegisterDatetime;
    private LocalDateTime userSuspensionEndDatetime;

}
