package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.Assessment;

public interface AssessmentRepository extends JpaRepository<Assessment, UUID> {
   
}
