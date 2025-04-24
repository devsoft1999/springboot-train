package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.UserMoodLog;

public interface UserMoodLogRepository extends JpaRepository<UserMoodLog, UUID> {
    
}
