package com.pakjai.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class AssessmentRequest {
    
    private UUID id;
   
    private Integer seq;

    private String question;

    private String type;

    private String status;

    private List<AssessmentAnswerRequest> answers;

}

