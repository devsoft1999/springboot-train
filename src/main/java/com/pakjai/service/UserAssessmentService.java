package com.pakjai.service;

import java.util.List;
import java.util.UUID;

import com.pakjai.dto.UserAssessmentRequest;
import com.pakjai.entity.UserAssessment;

public interface UserAssessmentService {

    List<UserAssessment> findAll();

    UserAssessment findById(UUID id);

    void save(UserAssessmentRequest assessmentRequest);

    void delete(UUID id);

}
