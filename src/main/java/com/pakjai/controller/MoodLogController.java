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

import com.pakjai.dto.MoodLogRequest;
import com.pakjai.entity.UserMoodLog;
import com.pakjai.service.UserMoodLogService;

@RestController
@RequestMapping("/api/mood")
public class MoodLogController {
    
    @Autowired
    private UserMoodLogService moodService;

    @GetMapping
    public List<UserMoodLog> findAll() {
        return moodService.findAll();
    }

    @GetMapping("/{id}")
    public UserMoodLog findById(@PathVariable UUID id) {
        return moodService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody MoodLogRequest userRequest) {
        moodService.save(userRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        moodService.delete(id);
    }
}
