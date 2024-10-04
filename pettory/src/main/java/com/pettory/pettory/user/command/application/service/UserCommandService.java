package com.pettory.pettory.user.command.application.service;

import com.pettory.pettory.exception.AlreadyRegisterException;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
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
@Slf4j
public class UserCommandService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    // 회원가입
    @Transactional
    public Long registerUser(UserRegisterRequest userRegisterRequest) {

        // 이메일 중복 체크
        if (userRepository.existsByUserEmail(userRegisterRequest.getUserEmail())) {
            throw new AlreadyRegisterException("이미 존재하는 이메일입니다.");
        }

        // 닉네임 중복 체크
        if (userRepository.existsByUserNickname(userRegisterRequest.getUserNickname())) {
            throw new AlreadyRegisterException("이미 존재하는 닉네임입니다.");
        }


        User newUser = UserMapper.toEntity(userRegisterRequest);
        newUser.encryptPassword(passwordEncoder.encode(newUser.getUserPassword())); // newUser의 plain pw를 암호화

        // repository에 저장

        User savedUser = userRepository.save(newUser);
        return savedUser.getUserId();
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
//    @Transactional
//    public void getNewPasswords(FindPasswordRequest findPasswordRequest) {
//
//        try {
//            User user = userRepository.findByUserEmail(findPasswordRequest.getUserEmail())
//                    .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));
//
//            // 임시 비밀번호 생성
//            String tempPassword = VerifyUtil.generateTempPassword();
//            user.updatePassword(passwordEncoder.encode(tempPassword));
//
//            // 이메일 발송
//            emailService.sendEmail(
//                    user.getUserEmail(),
//                    "펫토리 임시 비밀번호 안내",
//                    user.getUserNickname() + " 님의 임시 비밀번호는.\n" + tempPassword + "\n입니다. 로그인 후 반드시 비밀번호를 변경하세요."
//            );
//
//            // 변경된 비밀번호 저장
//            userRepository.save(user);
//        } catch (NotFoundException e) {
//            // 사용자가 없는 경우 예외 처리
//            // 필요에 따라 로깅을 추가하거나 사용자에게 적절한 메시지를 반환
//            throw new NotFoundException("회원을 찾을 수 없습니다.");
//        } catch (MailException e) {
//            // 이메일 전송 실패 시 예외 처리
//            // 예를 들어, 로그를 남기고 사용자에게 메시지를 반환할 수 있음
//            log.error("이메일 전송 실패: " + e.getMessage());
//            throw new com.pettory.pettory.exception.MailException("이메일 전송 중 오류가 발생했습니다. 다시 시도해 주세요.");
//        } catch (Exception e) {
//            // 그 외의 예외 처리
//            log.error("예상치 못한 오류 발생: " + e.getMessage());
//            throw new RuntimeException("시스템 오류가 발생했습니다. 관리자에게 문의하세요.");
//        }
//    }

    // 비밀번호 변경
    @Transactional
    public Long changePasswords(String userEmail, ChangePasswordRequest changePasswordRequest) {
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

        User changedUser = userRepository.save(user);
        return changedUser.getUserId();
    }

}
