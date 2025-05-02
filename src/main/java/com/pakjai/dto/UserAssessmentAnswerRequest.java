package com.pakjai.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class UserAssessmentAnswerRequest {
    private UUID id;

    private UUID userAssessmentId;

    private String answer;

    private Integer point;
}
