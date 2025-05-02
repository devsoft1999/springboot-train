package com.pakjai.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pakjai.dto.UserAssessmentRequest;
import com.pakjai.entity.UserAssessment;
import com.pakjai.service.UserAssessmentService;

@RestController
@RequestMapping("/api/userAssessments")
public class UserAssessmentController {
    
    @Autowired
    private UserAssessmentService userAssessmentService;

    @GetMapping
    public List<UserAssessment> findAll() {
        return userAssessmentService.findAll();
    }         

    @GetMapping("/{id}")
    public UserAssessment findById(@PathVariable UUID id) {
        return userAssessmentService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody UserAssessmentRequest userAssessmentRequest) {
        userAssessmentService.save(userAssessmentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userAssessmentService.delete(id);
    }
    
}
