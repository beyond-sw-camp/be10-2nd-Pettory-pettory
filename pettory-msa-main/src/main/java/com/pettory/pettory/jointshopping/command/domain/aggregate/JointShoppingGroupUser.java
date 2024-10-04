package com.pettory.pettory.jointshopping.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "joint_shopping_group_user_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)  // 엔티티 생성, 삭제 시점 체크를 위해 필요한 리스너
@SQLDelete(sql = "UPDATE joint_shopping_group_user_list " +
        "SET joint_shopping_user_delete_datetime = NOW() " +
        "WHERE joint_shopping_group_user_list_num = ?")
public class JointShoppingGroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jointShoppingGroupUserListNum;
    private Boolean resignYn = Boolean.FALSE;
    @CreatedDate
    private LocalDateTime jointShoppingUserInsertDatetime;
    private LocalDateTime jointShoppingUserDeleteDatetime;
    private Long jointShoppingGroupNum;
    private Long userId;

    public void changeResignYn() {
        this.resignYn = Boolean.TRUE;
    }
}
