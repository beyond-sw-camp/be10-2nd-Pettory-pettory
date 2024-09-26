package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupUserRepository;
import com.pettory.pettory.jointshopping.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public JointShoppingGroupUser saveGroupUser(JointShoppingGroupUser newJointShoppingGroupUser) {
        return jointShoppingGroupUserRepository.save(newJointShoppingGroupUser);
    }

    /* 도메인 객체를 즉시 저장하는 로직 */
    @Transactional
    public JointShoppingGroupUser saveAndFlushGroupUser(JointShoppingGroupUser newJointShoppingGroupUser) {
        return jointShoppingGroupUserRepository.saveAndFlush(newJointShoppingGroupUser);
    }

    /* 도메인 객체를 삭제하는 로직 */
    @Transactional
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



}
