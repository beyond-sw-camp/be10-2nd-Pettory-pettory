package com.pettory.mainserver.jointshopping.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "joint_shopping_participation_user_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)  // 엔티티 생성, 삭제 시점 체크를 위해 필요한 리스너
@SQLDelete(sql = "UPDATE joint_shopping_participation_user_list " +
        "SET participation_state = 'DELETE', joint_shopping_participation_delete_datetime = NOW() " +
        "WHERE joint_shopping_participation_user_list_num = ?")
public class JointShoppingParticipationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jointShoppingParticipationUserListNum;
    private Long paymentCost;
    @Enumerated(value = EnumType.STRING)
    private JointShoppingParticipationState participationState = JointShoppingParticipationState.ACTIVE;
    private String userCourierCode;
    private String userInvoiceNum;
    private Boolean productsReceiptYn = Boolean.FALSE;
    @CreatedDate
    private LocalDateTime jointShoppingParticipationInsertDatetime;
    private LocalDateTime jointShoppingParticipationDeleteDatetime;
    private Long jointShoppingGroupNum;
    private Long userId;

    // 물품 배송 정보를 변경하는 메소드
    public void update(String courierCode, String invoiceNum) {
        this.userCourierCode = courierCode;
        this.userInvoiceNum = invoiceNum;
    }

    // 물품 수령 여부를 변경하는 메소드
    public void changeProductsReceipt() {
        this.productsReceiptYn = Boolean.TRUE;
    }
}
