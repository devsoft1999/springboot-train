package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.AssessmentAnswer;

public interface AssessmentAnswerRepository extends JpaRepository<AssessmentAnswer, UUID> {
    
}
