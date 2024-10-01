package com.pettory.pettory.jointshopping.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JointShoppingGroupDTO {
    private Long jointShoppingGroupNum;
    private String jointShoppingGroupName;
    private String jointShoppingProducts;
    private String jointShoppingProductsState;
    private String jointShoppingProductsFileDirectory;
    private String jointShoppingInfo;
    private Integer jointShoppingCost;
    private Integer jointShoppingGroupMaximumCount;
    private Integer jointShoppingParticipationMaximumCount;
    private String hostCourierCode;
    private String hostInvoiceNum;
    private JointShoppingCategoryDTO category;
    private Long userId;
}
