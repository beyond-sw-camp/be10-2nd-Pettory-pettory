package com.pettory.pettory.jointshopping.query.dto;

import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

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
