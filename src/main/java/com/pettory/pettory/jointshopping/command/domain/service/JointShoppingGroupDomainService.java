package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import com.pettory.pettory.jointshopping.command.mapper.JointShoppingGroupMapper;
import com.pettory.pettory.jointshopping.util.FileUploadUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JointShoppingGroupDomainService {

    private String IMAGE_URL = "http://localhost:8080/joint-shopping-groupimgs/";
    private String IMAGE_DIR = "src/main/resources/static/jointshoppinggroupimgs/";

    private final JointShoppingGroupRepository jointShoppingGroupRepository;

    /* 도메인 객체를 생성하는 로직 */
    public JointShoppingGroup createGroup(JointShoppingGroupRequest groupRequest, MultipartFile productImg) {

        /* 전달 된 파일을 서버의 지정 경로에 저장
        * 파일이 없으면 저장 안함 */
        String replaceFileName = null;
        if(productImg != null) {
            replaceFileName = IMAGE_DIR + FileUploadUtils.saveFile(IMAGE_DIR, productImg);
        }

        /* dto to entity */
        JointShoppingGroup newJointShoppingGroup = JointShoppingGroupMapper.toEntity(groupRequest, replaceFileName);

        return newJointShoppingGroup;
    }

    /* 도메인 객체를 저장하는 로직 */
    public JointShoppingGroup saveGroup(JointShoppingGroup jointShoppingGroup) {
        return jointShoppingGroupRepository.save(jointShoppingGroup);
    }

    /* 도메인 객체를 수정하는 로직 */
    public void updateGroup(Long jointShoppingGroupNum, @Valid JointShoppingGroupRequest groupRequest, MultipartFile productImg) {

        JointShoppingGroup jointShoppingGroup = jointShoppingGroupRepository.findById(jointShoppingGroupNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 모임이 없습니다. code : " + jointShoppingGroupNum));

        /* 이미지 수정이 필요할 경우 새로운 이미지 저장 후 기존 이미지 삭제 */
        if(productImg != null) {
            String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, productImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, jointShoppingGroup.getJointShoppingProductsFileDirectory().replace(IMAGE_DIR, ""));
            jointShoppingGroup.changejointShoppingProductsFileDirectory(IMAGE_DIR + replaceFileName);
        }

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingGroup.update(
                groupRequest.getJointShoppingGroupName(),
                groupRequest.getJointShoppingProducts(),
                groupRequest.getJointShoppingInfo(),
                groupRequest.getJointShoppingCost(),
                groupRequest.getJointShoppingGroupMaximumCount(),
                groupRequest.getJointShoppingParticipationMaximumCount(),
                groupRequest.getHostInvoiceNum(),
                groupRequest.getJointShoppingCategoryNum(),
                groupRequest.getUserId()
        );
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteGroup(Long jointShoppingGroupNum) {
        /* soft delete 될 수 있도록 entity에 설정함 */
        jointShoppingGroupRepository.deleteById(jointShoppingGroupNum);
    }

    /* 최대 사용자 수를 반환하는 로직 */
    public Integer findGroupMaximumCount(Long jointShoppingGroupNum) {

        JointShoppingGroup jointShoppingGroup = jointShoppingGroupRepository.findById(jointShoppingGroupNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 모임이 없습니다. code : " + jointShoppingGroupNum));

        return jointShoppingGroup.getJointShoppingGroupMaximumCount();
    }

    /* 인원수가 가득 찼을 때 마감 상태로 변경하는 로직 */
    public void updateClosing(Long jointShoppingGroupNum) {
        JointShoppingGroup jointShoppingGroup = jointShoppingGroupRepository.findById(jointShoppingGroupNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 모임이 없습니다. code : " + jointShoppingGroupNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingGroup.changeClosing();
    }

    /* 나가거나 강퇴됬을 때 신청가능 상태로 변경하는 로직 */
    public void updateApplication(Long jointShoppingGroupNum) {
        JointShoppingGroup jointShoppingGroup = jointShoppingGroupRepository.findById(jointShoppingGroupNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 모임이 없습니다. code : " + jointShoppingGroupNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingGroup.changeApplication();
    }
}
