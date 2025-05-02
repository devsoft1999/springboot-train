package com.pakjai.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserAssessmentRequest {
    
    private UUID id;

    private Integer score;

    private String level;

    private String question;

    private List<UserAssessmentAnswerRequest> userAnswers;

}

