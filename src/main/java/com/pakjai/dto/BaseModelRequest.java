package com.pakjai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseModelRequest {

    @Schema(description = "ใส่รหัสไม่เกิน 3 ตัว", example = "001")
    private String code;

    private String name;

    private String description;

    private String status;

    
}
