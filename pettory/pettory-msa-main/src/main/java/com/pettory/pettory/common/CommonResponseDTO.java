package com.pettory.pettory.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter, setter, tostring, equals, hashcode 등 자동 추가
@NoArgsConstructor
public class CommonResponseDTO {
    private int statusCode;
    private String statusMessage;
    private Object result;

    public CommonResponseDTO(int statusCode, String statusMessage, Object result) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.result = result;
    }
}
