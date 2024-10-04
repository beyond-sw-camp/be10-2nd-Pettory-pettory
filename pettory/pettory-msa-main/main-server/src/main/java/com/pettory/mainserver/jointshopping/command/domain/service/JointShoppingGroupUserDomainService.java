package com.pettory.mainserver.jointshopping.command.domain.service;

import com.pettory.mainserver.exception.BadJoinException;
import com.pettory.mainserver.exception.NotFoundException;
import com.pettory.mainserver.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingGroupUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingGroupUserDomainService {

    private final JointShoppingGroupUserRepository jointShoppingGroupUserRepository;
    private final ModelMapper modelMapper;

    /* 도메인 객체를 생성하는 로직 */
    public JointShoppingGroupUser createGroupUser(JointShoppingGroupUserRequest groupUserRequest) {
        /* entity 생성 */
        JointShoppingGroupUser newJointShoppingGroupUser = modelMapper.map(groupUserRequest, JointShoppingGroupUser.class);

        return newJointShoppingGroupUser;
    }

    /* 도메인 객체를 저장하는 로직 */
    public JointShoppingGroupUser saveGroupUser(JointShoppingGroupUser newJointShoppingGroupUser) {
        return jointShoppingGroupUserRepository.save(newJointShoppingGroupUser);
    }

    /* 도메인 객체를 즉시 저장하는 로직 */
    public JointShoppingGroupUser saveAndFlushGroupUser(JointShoppingGroupUser newJointShoppingGroupUser) {
        return jointShoppingGroupUserRepository.saveAndFlush(newJointShoppingGroupUser);
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteGroupUser(Long jointShoppingGroupUserNum) {
        jointShoppingGroupUserRepository.deleteById(jointShoppingGroupUserNum);
    }

    /* 도메인 객체의 강퇴여부를 수정하는 로직 */
    public JointShoppingGroupUser updateResignYn(Long jointShoppingGroupUserNum) {

        JointShoppingGroupUser jointShoppingGroupUser = jointShoppingGroupUserRepository.findById(jointShoppingGroupUserNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 참가자가 없습니다. code : " + jointShoppingGroupUserNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingGroupUser.changeResignYn();

        return jointShoppingGroupUser;
    }

    /* 도메인 객체의 그룹 강퇴여부를 확인하는 로직  */
    public void checkResignYn(JointShoppingGroupUserRequest groupUserRequest) {
        List<JointShoppingGroupUser> jointShoppingGroupUserList  = jointShoppingGroupUserRepository.findByJointShoppingGroupNumAndUserIdAndResignYnTrue(
                groupUserRequest.getJointShoppingGroupNum(),
                groupUserRequest.getUserId()
        );

        if(!jointShoppingGroupUserList.isEmpty()){
           throw new BadJoinException("이미 강퇴당한 적이 있는 방입니다. ");
        }
    }

    /* 현재 사용자 수를 반환하는 로직 */
    public Integer findUserCount(Long jointShoppingGroupNum) {
        List<JointShoppingGroupUser> jointShoppingGroupUserList  = jointShoppingGroupUserRepository.findByJointShoppingGroupNum(jointShoppingGroupNum);

        return jointShoppingGroupUserList.size();
    }

    /* 그룹 번호를 반환하는 로직 */
    public Long findGroup(Long jointShoppingGroupUserNum) {
        JointShoppingGroupUser jointShoppingGroupUser = jointShoppingGroupUserRepository.findById(jointShoppingGroupUserNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 참가자가 없습니다. code : " + jointShoppingGroupUserNum));

        return jointShoppingGroupUser.getJointShoppingGroupNum();
    }
}
