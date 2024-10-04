package com.pettory.mainserver.jointshopping.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "joint_shopping_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JointShoppingCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jointShoppingCategoryNum;
    private String jointShoppingCategoryTitle;

    private JointShoppingCategory(String jointShoppingCategoryTitle){
        this.jointShoppingCategoryTitle = jointShoppingCategoryTitle;
    }

    // JointShoppingCategory 생성 메서드
    public static JointShoppingCategory create(String jointShoppingCategoryTitle) {
        return new JointShoppingCategory(jointShoppingCategoryTitle);
    }

    // 카테고리 변경하는 메소드
    public void update(String jointShoppingCategoryTitle) {
        this.jointShoppingCategoryTitle = jointShoppingCategoryTitle;
    }
}
