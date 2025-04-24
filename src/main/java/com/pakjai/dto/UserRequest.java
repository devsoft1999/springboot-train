package com.pakjai.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserRequest {
    
    private UUID id;

    private String email;

    private String password;

    private String nickname;

    private LocalDateTime userLastLogin;

    private List<MoodLogRequest> moods;
}
