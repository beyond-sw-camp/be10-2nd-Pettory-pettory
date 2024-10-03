package com.pettory.pettory.user.query.dto;

import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "ju@gmail.com")
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

    public UserInfoResponse(
            String userEmail, String userNickname, String userName, LocalDate userBirth, UserState userState, UserRole userRole, boolean userWalkingRecordPublicYn, String userHospitalName, String userHospitalInfo, LocalDateTime userRegisterDatetime, LocalDateTime userSuspensionEndDatetime
    ) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userState = userState;
        this.userRole = userRole;
        this.userWalkingRecordPublicYn = userWalkingRecordPublicYn;
        this.userHospitalName = userHospitalName;
        this.userHospitalInfo = userHospitalInfo;
        this.userRegisterDatetime = userRegisterDatetime;
        this.userSuspensionEndDatetime = userSuspensionEndDatetime;
    }
}
