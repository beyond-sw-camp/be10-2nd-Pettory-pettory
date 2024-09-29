package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupUserRepository;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationDomainService {

    private final JointShoppingParticipationRepository jointShoppingParticipationRepository;
    private final ModelMapper modelMapper;

    /* 도메인 객체를 생성하는 로직 */
    public JointShoppingParticipationUser createParticipation(JointShoppingParticipationRequest participationRequest) {
        /* entity 생성 */
        JointShoppingParticipationUser newJointShoppingParticipationUser
                = modelMapper.map(participationRequest, JointShoppingParticipationUser.class);

        return  newJointShoppingParticipationUser;
    }

    /* 도메인 객체를 저장하는 로직 */
    public JointShoppingParticipationUser saveParticipation(JointShoppingParticipationUser newJointShoppingParticipationUser) {
        return jointShoppingParticipationRepository.save(newJointShoppingParticipationUser);
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteParticipation(Long participationNum) {
        jointShoppingParticipationRepository.deleteById(participationNum);
    }
}
