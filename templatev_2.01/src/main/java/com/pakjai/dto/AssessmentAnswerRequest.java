package com.pakjai.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class AssessmentAnswerRequest {
    private UUID id;

    private UUID assessmentId;

    private Integer seq;

    private String answer;

    private Integer point;
}
