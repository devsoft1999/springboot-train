package com.pakjai.controller;

import com.pakjai.dto.BurnoutAdviceRequest;
import com.pakjai.entity.BurnoutAdvice;
import com.pakjai.service.BurnoutAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/burnout-advice")
public class BurnoutAdviceController {

    @Autowired
    private BurnoutAdviceService burnoutAdviceService;

    @GetMapping
    public List<BurnoutAdvice> findAll() {
        return burnoutAdviceService.findAll();
    }

    @GetMapping("/{id}")
    public BurnoutAdvice findById(@PathVariable UUID id) {
        return burnoutAdviceService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody BurnoutAdviceRequest burnoutAdviceRequest) {
        burnoutAdviceService.save(burnoutAdviceRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        burnoutAdviceService.delete(id);
    }
    
}
