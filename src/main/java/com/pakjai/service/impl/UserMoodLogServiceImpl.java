package com.pakjai.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.MoodLogRequest;
import com.pakjai.dto.UserRequest;
import com.pakjai.entity.User;
import com.pakjai.entity.UserMoodLog;
import com.pakjai.repository.UserMoodLogRepository;
import com.pakjai.repository.UserRepository;
import com.pakjai.service.UserMoodLogService;
import com.pakjai.service.mapper.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserMoodLogServiceImpl implements UserMoodLogService, ObjectMapper<MoodLogRequest,UserMoodLog> {
    
    @Autowired
    private UserMoodLogRepository userMoodLogRepository;

    @Autowired
    private UserRepository userRepository;

    public UserMoodLog toEntity(MoodLogRequest request) {
        UserMoodLog mood = Optional.ofNullable(request.getId())
                .flatMap(userMoodLogRepository::findById)
                .orElseGet(UserMoodLog::new);
    
        mood.setMood(request.getMood());
        mood.setNote(request.getNote());
        
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
            mood.setUser(user);
        }
        return mood;
    }
    

    public MoodLogRequest toDto(UserMoodLog userMoodLog) {
        MoodLogRequest userMoodLogRequest = new MoodLogRequest();
        if(userMoodLog.getId() != null){
            userMoodLogRequest.setId(userMoodLog.getId());
        }
        userMoodLogRequest.setMood(userMoodLog.getMood());
        userMoodLogRequest.setNote(userMoodLog.getNote());
        userMoodLogRequest.setUserId(userMoodLog.getUser().getId());

        return userMoodLogRequest;
    }

    @Override
    public List<UserMoodLog> findAll() {
        log.info("Finding all users");
        try{
            return userMoodLogRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all users : " + e.getMessage());
        }
    }

    @Override
    public UserMoodLog findById(UUID id) {
        log.info("Finding user with id: {}", id);
        try{
            return userMoodLogRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding user : " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public void save(MoodLogRequest moodLog) {
        log.info("Saving mood: {}", moodLog);
        try{
            UserMoodLog mood = toEntity(moodLog);

            log.info("mood: {}", mood.getId());
            userMoodLogRepository.save(mood);
        }catch(Exception e){
            throw new RuntimeException("Error saving mood : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting mood with id: {}", id);
        try{
            userMoodLogRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting mood : " + e.getMessage());
        }
    }
}
