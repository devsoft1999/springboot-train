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

import com.pakjai.dto.AssessmentRequest;
import com.pakjai.entity.Assessment;
import com.pakjai.entity.User;
import com.pakjai.service.AssessmentService;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    
    @Autowired
    private AssessmentService assessmentService;

    @GetMapping
    public List<Assessment> findAll() {
        return assessmentService.findAll();
    }         

    @GetMapping("/{id}")
    public Assessment findById(@PathVariable UUID id) {
        return assessmentService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody AssessmentRequest assessmentRequest) {
        assessmentService.save(assessmentRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        assessmentService.delete(id);
    }


    @GetMapping("/users")
    public List<User> findAllUser() {
        return assessmentService.findAllUser();
    }
}
