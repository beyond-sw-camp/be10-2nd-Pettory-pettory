package com.pettory.pettory.user.command.application.service;

import com.pettory.pettory.security.util.EmailService;
import com.pettory.pettory.exception.AlreadyResignException;
import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.exception.UnauthorizedException;
import com.pettory.pettory.family.command.domain.aggregate.Family;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.aggregate.PetAccess;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.security.util.VerifyUtil;
import com.pettory.pettory.user.command.application.dto.ChangePasswordRequest;
import com.pettory.pettory.user.command.application.dto.FindPasswordRequest;
import com.pettory.pettory.user.command.application.dto.UserDeleteRequest;
import com.pettory.pettory.user.command.application.dto.UserRegisterRequest;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.user.command.mapper.UserMapper;
import com.pettory.pettory.walkingRecord.command.domain.aggregate.WalkingRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserCommandService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    // 회원가입
    @Transactional
    public void registerUser(UserRegisterRequest userRegisterRequest) {

        User newUser = UserMapper.toEntity(userRegisterRequest);
        newUser.encryptPassword(passwordEncoder.encode(newUser.getUserPassword())); // newUser의 plain pw를 암호화

        // repository에 저장
        userRepository.save(newUser);
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(String userEmail, UserDeleteRequest userDeleteRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 사용자 상태가 'DELETE' 인 경우 DisabledException 또는 커스텀 예외 발생
        if (user.getUserState() == UserState.DELETE) {
            throw new AlreadyResignException("탈퇴한 회원입니다.");
        }

        // 비밀번호 검증
        if (!passwordEncoder.matches(userDeleteRequest.getUserPassword(), user.getUserPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        // 회원 상태 변경
        user.updateUserAsDelete();

        // 펫 상태 변경
        for (Pet pet : user.getPets()) {
            pet.updatePetAsDelete();
        }

        // 펫 접근 상태 변경
        for (PetAccess petAccess : user.getPetAccesses()) {
            petAccess.updatePetAccessAsDelete();
        }

        // 산책 기록 상태 변경
        for (WalkingRecord walkingRecord : user.getWalkingRecords()) {
            walkingRecord.updateWalkingRecordAsDelete();
        }

        // 급식 기록 상태 변경
        for (FeedingRecord feedingRecord : user.getFeedingRecords()) {
            feedingRecord.updateFeedingRecordAsDelete();
        }

        Family family = user.getFamily();

        if (family != null) {
            if (family.getFamilyOwnerId().equals(user.getUserId())) {
                if (family.getFamilyNumber() == 1) {
                    family.updateFamilyStateAsDeleted();
                } else {
                    family.reduceFamilyNumber();
                    user.updateFamilyIdAsNull();
                }
            } else {
                family.reduceFamilyNumber();
                user.updateFamilyIdAsNull();
            }
        }

    }

    // 커스텀 로그인 필터 동작 시 인증 매니저에 의해 자동으로 호출되는 메소드
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // 인증 토큰에 담긴 userId가 메소드로 넘어오므로 해당 값을 기준으로 DB에서 조회한다.
        User loginUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(userEmail + "가 존재하지 않습니다."));


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + loginUser.getUserRole().name()));

        // id(email), pw, 권한
        return new org.springframework.security.core.userdetails.User(loginUser.getUserEmail(), loginUser.getUserPassword(), grantedAuthorities);
    }

    // 새로운 비밀번호 전송
    @Transactional
    public void getNewPasswords(FindPasswordRequest findPasswordRequest) {

        User user = userRepository.findByUserEmail(findPasswordRequest.getUserEmail())
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 임시 비밀번호 생성
        String tempPassword = VerifyUtil.generateTempPassword();
        user.updatePassword(passwordEncoder.encode(tempPassword));

        // 이메일 발송
        emailService.sendEmail(
                user.getUserEmail(),
                "펫토리 임시 비밀번호 안내",
                user.getUserNickname() + " 님의 임시 비밀번호는.\n" + tempPassword + "\n입니다. 로그인 후 반드시 비밀번호를 변경하세요.");

        userRepository.save(user);
    }

    // 비밀번호 변경
    @Transactional
    public void changePasswords(String userEmail, ChangePasswordRequest changePasswordRequest) {
        UserSecurity.validateCurrentUser(userEmail);

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 기존 비밀번호 검증
        if (!passwordEncoder.matches(
                changePasswordRequest.getCurrentUserPassword(), user.getUserPassword()
        )) {
            throw new UnauthorizedException("기존 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 변경
        user.updatePassword(passwordEncoder.encode(changePasswordRequest.getNewUserPassword()));

        // 비밀번호 변경 후 이메일 알림
        emailService.sendEmail(
                user.getUserEmail(),
                "펫토리 비밀번호 변경 안내",
                user.getUserNickname() + " 님의 비밀번호가 변경되었습니다.\n" +
                        "본인이 비밀번호를 변경하지 않았다면 mypettory@gmail.com 으로 문의 바랍니다."
        );

        userRepository.save(user);
    }

    // 닉네임으로 이메일 찾기 - 인증 코드 전송
    @Transactional
    public void sendVerifyCode(String userNickname) {
        User user = userRepository.findByUserNickname(userNickname)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        String verifyCode = VerifyUtil.generateVerifyCode();

        // 이메일로 인증 코드 전송
        emailService.sendEmail(
                user.getUserEmail(),
                "펫토리 인증 코드 안내",
                user.getUserNickname() + " 님의 이메일 찾기 인증 코드는 " +
                        verifyCode +" 입니다."
        );
    }

    public void checkCode(String userEmail, String verifyCode) {
        if (!verificationService.(userEmail, verifyCode)) {
            throw new UnauthorizedException("인증 코드가 일치하지 않습니다.");
        }
    }

    // 닉네임으로 이메일 찾기 - 인증 코드 검증
}
