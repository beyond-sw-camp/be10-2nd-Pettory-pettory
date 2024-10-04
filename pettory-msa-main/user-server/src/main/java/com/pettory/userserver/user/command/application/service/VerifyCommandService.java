package com.pettory.userserver.user.command.application.service;

import com.pettory.userserver.security.util.EmailService;
import com.pettory.userserver.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerifyCommandService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final Map<String, String> verifyCodes = new HashMap<>();



    // 닉네임으로 이메일 찾기 - 인증 코드 전송
//    @Transactional
//    public void sendVerifyCodeByUserNickname(String userNickname) {
//        User user = userRepository.findByUserNickname(userNickname)
//                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));
//
//        // 인증 코드 생성
//        String verifyCode = VerifyUtil.generateVerifyCode();
//
//        // 인증 코드 저장
//        saveVerifyCode(user.getUserEmail(), verifyCode);
//
//        // 이메일로 인증 코드 전송
//        emailService.sendEmail(
//                user.getUserEmail(),
//                "펫토리 인증 코드 안내",
//                user.getUserNickname() + " 님의 이메일 찾기 인증 코드는 " +
//                        verifyCode +" 입니다."
//        );
//    }

    // 인증 코드 저장
//    public void saveVerifyCode(String userEmail, String verifyCode) {
//        verifyCodes.put(userEmail, verifyCode);
//    }
//
//    // 인증 코드 검증
//    public boolean isCodeValid(String userEmail, String verifyCode) {
//        return verifyCode.equals(verifyCodes.get(userEmail));
//    }
//
//    // 검증에 성공하면 인증 코드 삭제
//    public void removeVerifyCode(String userEmail) {
//        verifyCodes.remove(userEmail);
//    }
//
//    // 닉네임으로 이메일 찾기 - 인증 코드 검증
//    @Transactional
//    public void checkCode(String userEmail, String verifyCode) {
//        if (!isCodeValid(userEmail, verifyCode)) {
//            throw new UnauthorizedException("인증 코드가 일치하지 않습니다.");
//        }
//        // 검증 성공하면 인증 코드 삭제
//        removeVerifyCode(userEmail);
//    }
}
