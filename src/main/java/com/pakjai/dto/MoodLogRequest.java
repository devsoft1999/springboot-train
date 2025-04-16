package com.pakjai.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class MoodLogRequest {
    
    @Schema(description = "อารมณ์ของคุณวันนี้", example = "😐")
    private String mood;  

    @Schema(description = "ข้อความเพิ่มเติม", example = "รู้สึกเฉยๆ แต่โอเค")
    private String note;       
}
