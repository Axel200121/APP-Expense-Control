package com.ams.developer.api.expense.control.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponseDto {
    private Integer codeStatus;
    private String description;
    private Object data;

    public ApiResponseDto(Integer codeStatus, String description) {
        this.codeStatus = codeStatus;
        this.description = description;
    }
}
