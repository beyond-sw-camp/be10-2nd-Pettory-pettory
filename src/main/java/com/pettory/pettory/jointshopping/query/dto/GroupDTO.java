package com.pettory.pettory.jointshopping.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupDTO {
    private Long jointShoppingGroupNum;
    private String jointShoppingGroupName;
    private String jointShoppingProducts;
    private Character jointShoppingProductsState;
    private String jointShoppingProductsFileDirectory;
    private String jointShoppingInfo;
    private Integer jointShoppingCost;
    private Integer jointShoppingGroupMaximumCount;
    private Integer jointShoppingParticipationMaximumCount;
    private String hostInvoiceNum;
    private CategoryDTO category;
    private Long userId;
}
