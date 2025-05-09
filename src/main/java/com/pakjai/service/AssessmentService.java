package com.pakjai.service;

import java.util.List;
import java.util.UUID;

import com.pakjai.dto.AssessmentRequest;
import com.pakjai.entity.Assessment;
import com.pakjai.entity.User;

public interface AssessmentService {

    List<Assessment> findAll();

    Assessment findById(UUID id);

    void save(AssessmentRequest assessmentRequest);

    void delete(UUID id);

    List<User> findAllUser();

}
