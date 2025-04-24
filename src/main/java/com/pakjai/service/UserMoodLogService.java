package com.pakjai.service;

import java.util.List;
import java.util.UUID;

import com.pakjai.dto.MoodLogRequest;
import com.pakjai.entity.UserMoodLog;

public interface UserMoodLogService {
    
    List<UserMoodLog> findAll();

    UserMoodLog findById(UUID id);

    void save(MoodLogRequest moodLogRequest);

    void delete(UUID id);
}
