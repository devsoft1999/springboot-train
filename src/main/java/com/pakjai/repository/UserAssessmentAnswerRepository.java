package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.UserAssessmentAnswer;

public interface UserAssessmentAnswerRepository extends JpaRepository<UserAssessmentAnswer, UUID> {
    
}
