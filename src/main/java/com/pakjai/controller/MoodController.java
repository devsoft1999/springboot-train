package com.pakjai.controller;

import com.pakjai.dto.MoodLogRequest;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/api/mood")
@Slf4j
public class MoodController {

    @PostMapping("/log")
    public String logMood(@RequestBody MoodLogRequest request) {
        log.info("Received mood log request: {}", request);
        return "Mood received: " + request.getMood() + " | " + request.getNote();
    }
}
