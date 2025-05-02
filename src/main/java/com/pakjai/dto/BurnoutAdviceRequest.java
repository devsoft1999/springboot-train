package com.pakjai.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BurnoutAdviceRequest {
    private UUID id;
    private String level;
    private String title;
    private String message;
}
