package com.pettory.pettory.jointshopping.command.domain.aggregate;

import com.pettory.pettory.exception.BadJoinException;
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
@SQLDelete(sql = "UPDATE joint_shopping_group " +
        "SET joint_shopping_group_state = 'DELETE', joint_shopping_group_delete_datetime = NOW() " +
        "WHERE joint_shopping_group_num = ?")
public class JointShoppingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jointShoppingGroupNum;
    private String jointShoppingGroupName;
    private String jointShoppingProducts;
    @Enumerated(value = EnumType.STRING)
    private JointShoppingProductsState jointShoppingProductsState = JointShoppingProductsState.Recruitment;
    private String jointShoppingProductsFileDirectory;
    private String jointShoppingInfo;
    private Integer jointShoppingCost;
    private Integer jointShoppingGroupMaximumCount;
    private Integer jointShoppingParticipationMaximumCount;
    private String hostCourierCode;
    private String hostInvoiceNum;
    @Enumerated(value = EnumType.STRING)
    private JointShoppingGroupState jointShoppingGroupState = JointShoppingGroupState.APPLICATION;
    @CreatedDate
    private LocalDateTime jointShoppingGroupInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime jointShoppingGroupUpdateDatetime;
    private LocalDateTime jointShoppingGroupDeleteDatetime;
    private Long jointShoppingCategoryNum;
    private Long userId;

    private JointShoppingGroup(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingProductsFileDirectory, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount, String hostCourierCode, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId) {
        this.jointShoppingGroupName = jointShoppingGroupName;
        this.jointShoppingProducts = jointShoppingProducts;
        this.jointShoppingProductsFileDirectory = jointShoppingProductsFileDirectory;
        this.jointShoppingInfo = jointShoppingInfo;
        this.jointShoppingCost = jointShoppingCost;
        this.jointShoppingGroupMaximumCount = jointShoppingGroupMaximumCount;
        this.jointShoppingParticipationMaximumCount = jointShoppingParticipationMaximumCount;
        this.hostCourierCode = hostCourierCode;
        this.hostInvoiceNum = hostInvoiceNum;
        this.jointShoppingCategoryNum = jointShoppingCategoryNum;
        this.userId = userId;
    }

    // JointShoppingGroup 생성 메소드
    public static JointShoppingGroup create(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingProductsFileDirectory, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount,String hostCourierCode, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId){
        return new JointShoppingGroup(jointShoppingGroupName, jointShoppingProducts, jointShoppingProductsFileDirectory, jointShoppingInfo, jointShoppingCost, jointShoppingGroupMaximumCount, jointShoppingParticipationMaximumCount, hostCourierCode, hostInvoiceNum, jointShoppingCategoryNum, userId);
    }

    // jointShoppingProductsFileDirectory 변경 메소드
    public void changejointShoppingProductsFileDirectory(String jointShoppingProductsFileDirectory) {
        this.jointShoppingProductsFileDirectory = jointShoppingProductsFileDirectory;
    }

    // jointShoppingGroupState 변경 메소드
    public void changeClosing() {
        this.jointShoppingGroupState = JointShoppingGroupState.CLOSE;
    }

    public void changeApplication() {
        this.jointShoppingGroupState = JointShoppingGroupState.APPLICATION;
    }

    // 다른 필드를 변경하는 메소드
    public void update(String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingInfo, Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount, Integer jointShoppingParticipationMaximumCount, String hostCourierCode, String hostInvoiceNum, Long jointShoppingCategoryNum, Long userId) {
        this.jointShoppingGroupName = jointShoppingGroupName;
        this.jointShoppingProducts = jointShoppingProducts;
        this.jointShoppingInfo = jointShoppingInfo;
        this.jointShoppingCost = jointShoppingCost;
        this.jointShoppingGroupMaximumCount = jointShoppingGroupMaximumCount;
        this.jointShoppingParticipationMaximumCount = jointShoppingParticipationMaximumCount;
        this.hostCourierCode = hostCourierCode;
        this.hostInvoiceNum = hostInvoiceNum;
        this.jointShoppingCategoryNum = jointShoppingCategoryNum;
        this.userId = userId;
    }

    /* 배송 정보를 변경하는 메소드 */
    public void updateDeliveryInfo(String courierCode, String invoiceNum) {
        this.hostCourierCode = courierCode;
        this.hostInvoiceNum = invoiceNum;
    }

    /* 배송 상태를 변경하는 메소드 */
    public void changeProductsState(JointShoppingProductsState jointShoppingProductsState) {
        if(jointShoppingProductsState.equals(JointShoppingProductsState.Recruitment))
            this.jointShoppingProductsState = JointShoppingProductsState.Delivery;
        else if(jointShoppingProductsState.equals(JointShoppingProductsState.Delivery))
            this.jointShoppingProductsState = JointShoppingProductsState.OrderCompleted;
        else if(jointShoppingProductsState.equals(JointShoppingProductsState.OrderCompleted))
            this.jointShoppingProductsState = JointShoppingProductsState.Arrival;
    }
}
