package com.pakjai.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserBurnoutAdviceLogRequest {
    private UUID id;
    private UUID userId;
    private UUID burnoutAdviceId;
}
