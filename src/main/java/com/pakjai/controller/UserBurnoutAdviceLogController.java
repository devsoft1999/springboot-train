package com.pakjai.controller;

import com.pakjai.dto.UserBurnoutAdviceLogRequest;
import com.pakjai.dto.MoodLogRequest;
import com.pakjai.entity.BurnoutAdvice;
import com.pakjai.entity.UserBurnoutAdviceLog;
import com.pakjai.entity.UserMoodLog;
import com.pakjai.service.UserBurnoutAdviceLogService;
import com.pakjai.service.UserMoodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-burnout-advice-log")
public class UserBurnoutAdviceLogController {

    @Autowired
    private UserBurnoutAdviceLogService userBurnoutAdviceLogService;

    @GetMapping
    public List<UserBurnoutAdviceLog> findAll() {
        return userBurnoutAdviceLogService.findAll();
    }

    @GetMapping("/{id}")
    public  UserBurnoutAdviceLog findById(@PathVariable UUID id) {
        return userBurnoutAdviceLogService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody UserBurnoutAdviceLogRequest userBurnoutAdviceLogRequest) {
        userBurnoutAdviceLogService.save(userBurnoutAdviceLogRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        userBurnoutAdviceLogService.delete(id);
    }
    
}
