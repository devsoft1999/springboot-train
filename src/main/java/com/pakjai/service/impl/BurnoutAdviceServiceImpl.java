package com.pakjai.service.impl;

import com.pakjai.dto.BurnoutAdviceRequest;
import com.pakjai.entity.BurnoutAdvice;
import com.pakjai.repository.BurnoutAdviceRepository;
import com.pakjai.service.BurnoutAdviceService;
import com.pakjai.service.mapper.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class BurnoutAdviceServiceImpl implements BurnoutAdviceService,ObjectMapper<BurnoutAdviceRequest, BurnoutAdvice> {
    
    @Autowired
    private BurnoutAdviceRepository burnoutAdviceRepository;

    public BurnoutAdvice toEntity(BurnoutAdviceRequest request) {
        BurnoutAdvice burnoutAdvice = Optional.ofNullable(request.getId())
                .flatMap(burnoutAdviceRepository::findById)
                .orElseGet(BurnoutAdvice::new);

        burnoutAdvice.setLevel(request.getLevel());
        burnoutAdvice.setTitle(request.getTitle());
        burnoutAdvice.setMessage(request.getMessage());

        return burnoutAdvice;
    }
    
    public BurnoutAdviceRequest toDto(BurnoutAdvice burnoutAdvice) {
        BurnoutAdviceRequest burnoutAdviceRequest = new BurnoutAdviceRequest();
        if(burnoutAdviceRequest.getId() != null){
            burnoutAdviceRequest.setId(burnoutAdvice.getId());
        }
        burnoutAdviceRequest.setLevel(burnoutAdvice.getLevel());
        burnoutAdviceRequest.setTitle(burnoutAdvice.getTitle());
        burnoutAdviceRequest.setMessage(burnoutAdvice.getMessage());

        return burnoutAdviceRequest;
    }

    @Override
    public List<BurnoutAdvice> findAll() {
        log.info("Finding all Burnout Advice");
        try{
            return burnoutAdviceRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all Burnout Advice : " + e.getMessage());
        }
    }

    @Override
    public BurnoutAdvice findById(UUID id) {
        log.info("Finding Burnout Advice with id: {}", id);
        try{
            return burnoutAdviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Burnout Advice not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding Burnout Advice : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void save(BurnoutAdviceRequest burnoutAdviceRequest) {
        log.info("Saving  Burnout Advice: {}", burnoutAdviceRequest);
        try{
            BurnoutAdvice burnoutAdvice = toEntity(burnoutAdviceRequest);
            burnoutAdviceRepository.save(burnoutAdvice);
        }catch(Exception e){
            throw new RuntimeException("Error saving Burnout Advice : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting Burnout Advice with id: {}", id);
        try{
            burnoutAdviceRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting Burnout Advice : " + e.getMessage());
        }
    }
}
