package com.pakjai.dto;

import lombok.Data;

import java.util.UUID;

import com.pakjai.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class MoodLogRequest {
    private UUID id;

    private UUID userId;
    
    @Schema(description = "อารมณ์ของคุณวันนี้", example = "😐")
    private String mood;  

    @Schema(description = "ข้อความเพิ่มเติม", example = "รู้สึกเฉยๆ แต่โอเค")
    private String note;       
}
