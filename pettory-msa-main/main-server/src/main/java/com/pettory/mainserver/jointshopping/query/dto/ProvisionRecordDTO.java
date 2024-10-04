package com.pettory.mainserver.jointshopping.query.dto;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.ProvisionState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "지급기록 DTO")
public class ProvisionRecordDTO {

    private Long provisionRecordNum;
    private Integer provisionCost;
    private String provisionState;
    private Long jointShoppingGroupNum;

}
