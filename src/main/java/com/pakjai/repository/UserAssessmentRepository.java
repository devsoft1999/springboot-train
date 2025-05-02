package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.UserAssessment;

public interface UserAssessmentRepository extends JpaRepository<UserAssessment, UUID> {
   
}
