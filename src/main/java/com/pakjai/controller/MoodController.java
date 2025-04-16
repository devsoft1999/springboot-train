package com.pakjai.controller;

import com.pakjai.dto.MoodLogRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mood")
public class MoodController {

    @PostMapping("/log")
    public String logMood(@RequestBody MoodLogRequest request) {
        return "Mood received: " + request.getMood() + " | " + request.getNote();
    }
}
