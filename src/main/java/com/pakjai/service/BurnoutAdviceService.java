package com.pakjai.service;

import com.pakjai.dto.BurnoutAdviceRequest;
import com.pakjai.entity.BurnoutAdvice;

import java.util.List;
import java.util.UUID;

public interface BurnoutAdviceService {

    List<BurnoutAdvice> findAll();

    BurnoutAdvice findById(UUID id);

    void save(BurnoutAdviceRequest burnoutAdviceRequest);

    void delete(UUID id);

}
