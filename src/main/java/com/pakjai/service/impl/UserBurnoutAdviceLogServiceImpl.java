package com.pakjai.service.impl;

import com.pakjai.dto. UserBurnoutAdviceLogRequest;
import com.pakjai.entity.BurnoutAdvice;
import com.pakjai.entity.User;
import com.pakjai.entity. UserBurnoutAdviceLog;
import com.pakjai.repository. UserBurnoutAdviceLogRepository;
import com.pakjai.service. UserBurnoutAdviceLogService;
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
public class UserBurnoutAdviceLogServiceImpl implements  UserBurnoutAdviceLogService,ObjectMapper< UserBurnoutAdviceLogRequest,  UserBurnoutAdviceLog> {
    
    @Autowired
    private  UserBurnoutAdviceLogRepository userBurnoutAdviceLogRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private BurnoutAdviceServiceImpl burnoutAdviceServiceImpl;

    public  UserBurnoutAdviceLog toEntity( UserBurnoutAdviceLogRequest request) {
         UserBurnoutAdviceLog userBurnoutAdviceLog = Optional.ofNullable(request.getId())
            .flatMap(userBurnoutAdviceLogRepository::findById)
            .orElseGet(UserBurnoutAdviceLog::new);

        if (request.getBurnoutAdviceId() != null) {
            BurnoutAdvice burnoutAdvice = burnoutAdviceServiceImpl.findById(request.getBurnoutAdviceId());
            userBurnoutAdviceLog.setBurnoutAdvice(burnoutAdvice);
        }

        if (request.getUserId() != null) {
            User user = userServiceImpl.findById(request.getUserId());
            userBurnoutAdviceLog.setUser(user);
        }

        return userBurnoutAdviceLog;
    }

    public  UserBurnoutAdviceLogRequest toDto( UserBurnoutAdviceLog userBurnoutAdviceLog) {
         UserBurnoutAdviceLogRequest userBurnoutAdviceLogRequest = new  UserBurnoutAdviceLogRequest();
        if(userBurnoutAdviceLog.getId() != null){
            userBurnoutAdviceLogRequest.setId(userBurnoutAdviceLog.getId());
        }

        if(userBurnoutAdviceLog.getBurnoutAdvice() != null){
            userBurnoutAdviceLogRequest.setBurnoutAdviceId(userBurnoutAdviceLog.getBurnoutAdvice().getId());
        }

        if(userBurnoutAdviceLog.getUser() != null){
            userBurnoutAdviceLogRequest.setUserId(userBurnoutAdviceLog.getUser().getId());
        }

        return userBurnoutAdviceLogRequest;
    }

    @Override
    public List< UserBurnoutAdviceLog> findAll() {
        log.info("Finding all User Burnout Advice Log");
        try{
            return userBurnoutAdviceLogRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all User Burnout Advice Log : " + e.getMessage());
        }
    }

    @Override
    public  UserBurnoutAdviceLog findById(UUID id) {
        log.info("FindingUser Burnout Advice Log with id: {}", id);
        try{
            return userBurnoutAdviceLogRepository.findById(id).orElseThrow(() -> new RuntimeException("User Burnout Advice Log not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding User Burnout Advice Log : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void save( UserBurnoutAdviceLogRequest userAssessmentAnswerRequest) {
        log.info("Saving userAssessmentAnswer: {}", userAssessmentAnswerRequest);
        try{
             UserBurnoutAdviceLog userAssessmentAnswer = toEntity(userAssessmentAnswerRequest);
            userBurnoutAdviceLogRepository.save(userAssessmentAnswer);
        }catch(Exception e){
            throw new RuntimeException("Error saving User Burnout Advice Log : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting User Burnout Advice Log with id: {}", id);
        try{
            userBurnoutAdviceLogRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting User Burnout Advice Log : " + e.getMessage());
        }
    }
}
