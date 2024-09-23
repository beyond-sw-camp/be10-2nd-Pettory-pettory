package com.pettory.pettory.jointshopping.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "joint_shopping_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)  // 엔티티 생성, 삭제 시점 체크를 위해 필요한 리스너
@SQLDelete(sql = "UPDATE joint_shopping_group SET joint_shopping_group_state = 'D', joint_shopping_group_delete_datetime = NOW() WHERE joint_shopping_group_num = ?")
public class JointShoppingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jointShoppingGroupNum;
    private String jointShoppingGroupName;
    private String jointShoppingProducts;
    @Enumerated(value = EnumType.STRING)
    private JointShoppingProductStatus jointShoppingProductStatus = JointShoppingProductStatus.Recruitment;
    private String jointShoppingProductsFileDirectory;
    private String jointShoppingInfo;
    private Integer jointShoppingCost;
    private Integer jointShoppingGroupMaximumCount;
    private Integer jointShoppingParticipationMaximumCount;
    private String hostInvoiceNum;
    @Enumerated(value = EnumType.STRING)
    private JointShoppingGroupStatus jointShoppingGroupStatus = JointShoppingGroupStatus.APPLICATION;
    @CreatedDate
    private LocalDateTime joint_shopping_group_insert_datetime;
    @LastModifiedDate
    private LocalDateTime joint_shopping_group_update_datetime;
    private LocalDateTime joint_shopping_group_delete_datetime;
    private Long jointShoppingCategoryNum;
    private Long userId;

    public JointShoppingGroup(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingProductsFileDirectory, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId) {
        this.jointShoppingGroupName = jointShoppingGroupName;
        this.jointShoppingProducts = jointShoppingProducts;
        this.jointShoppingProductsFileDirectory = jointShoppingProductsFileDirectory;
        this.jointShoppingInfo = jointShoppingInfo;
        this.jointShoppingCost = jointShoppingCost;
        this.jointShoppingGroupMaximumCount = jointShoppingGroupMaximumCount;
        this.jointShoppingParticipationMaximumCount = jointShoppingParticipationMaximumCount;
        this.hostInvoiceNum = hostInvoiceNum;
        this.jointShoppingCategoryNum = jointShoppingCategoryNum;
        this.userId = userId;
    }

    // JointShoppingGroup 생성 메소드
    public static JointShoppingGroup create(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingProductsFileDirectory, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId){
        return new JointShoppingGroup(jointShoppingGroupName, jointShoppingProducts, jointShoppingProductsFileDirectory, jointShoppingInfo, jointShoppingCost, jointShoppingGroupMaximumCount, jointShoppingParticipationMaximumCount, hostInvoiceNum, jointShoppingCategoryNum, userId);
    }

    // jointShoppingProductsFileDirectory 변경 메소드
    public void changejointShoppingProductsFileDirectory(String jointShoppingProductsFileDirectory) {
        this.jointShoppingProductsFileDirectory = jointShoppingProductsFileDirectory;
    }

    // 다른 필드를 변경하는 메소드
    public void update(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId) {
        this.jointShoppingGroupName = jointShoppingGroupName;
        this.jointShoppingProducts = jointShoppingProducts;
        this.jointShoppingInfo = jointShoppingInfo;
        this.jointShoppingCost = jointShoppingCost;
        this.jointShoppingGroupMaximumCount = jointShoppingGroupMaximumCount;
        this.jointShoppingParticipationMaximumCount = jointShoppingParticipationMaximumCount;
        this.hostInvoiceNum = hostInvoiceNum;
        this.jointShoppingCategoryNum = jointShoppingCategoryNum;
        this.userId = userId;
    }
}
