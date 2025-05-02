package com.pakjai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.AssessmentAnswerRequest;
import com.pakjai.dto.AssessmentRequest;
import com.pakjai.dto.MoodLogRequest;
import com.pakjai.dto.UserAssessmentAnswerRequest;
import com.pakjai.dto.UserAssessmentRequest;
import com.pakjai.dto.UserRequest;
import com.pakjai.entity.Assessment;
import com.pakjai.entity.User;
import com.pakjai.entity.UserAssessment;
import com.pakjai.entity.UserAssessmentAnswer;
import com.pakjai.entity.UserMoodLog;
import com.pakjai.repository.UserRepository;
import com.pakjai.repository.UserMoodLogRepository;
import com.pakjai.repository.UserAssessmentRepository;
import com.pakjai.service.UserAssessmentService;
import com.pakjai.service.UserService;
import com.pakjai.service.mapper.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, ObjectMapper<UserRequest, User> {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMoodLogRepository userMoodLogRepository;
    @Autowired
    private UserMoodLogServiceImpl userMoodLogServiceImpl;

    @Autowired
    private UserAssessmentRepository userAssessmentRepository;

    @Autowired
    private UserAssessmentServiceImpl userAssessmentServiceImpl;

    public User toEntity(UserRequest request) {
        User user = Optional.ofNullable(request.getId())
            .map(this::findById)
            .orElseGet(User::new);

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setLastLogin(request.getUserLastLogin());

        if (user.getMoods() == null) {
            user.setMoods(new ArrayList<>());
        }

        if (user.getUserAssessments() == null) {
            user.setUserAssessments(new ArrayList<>());
        }

        Map<UUID, UserMoodLog> existingMoodMap = user.getMoods().stream()
                .filter(a -> a.getId() != null)
                .collect(Collectors.toMap(UserMoodLog::getId, a -> a));
    
        List<UserMoodLog> updatedUserMoodLog = new ArrayList<>();

        Optional.ofNullable(request.getMoods()).ifPresent(moodLogRequests -> {
            for (MoodLogRequest moodLogRequest : moodLogRequests) {
                UserMoodLog userMoodLog = userMoodLogServiceImpl.toEntity(moodLogRequest);
                userMoodLog.setUser(user);
                updatedUserMoodLog.add(userMoodLog);
                existingMoodMap.remove(userMoodLog.getId());
            }
        });

        user.getMoods().removeIf(a -> existingMoodMap.containsKey(a.getId()));
    
        user.getMoods().clear();
        user.getMoods().addAll(updatedUserMoodLog);

        Map<UUID, UserAssessment> existingUserAssessmentsMap = user.getUserAssessments().stream()
                .filter(a -> a.getId() != null)
                .collect(Collectors.toMap(UserAssessment::getId, a -> a));
    
        List<UserAssessment> updatedUserAssessments = new ArrayList<>();
    
        Optional.ofNullable(request.getUserAssessments()).ifPresent(userAssessmentRequests -> {
            for (UserAssessmentRequest userAssessmentRequest : userAssessmentRequests) {
                UserAssessment userAssessment = userAssessmentServiceImpl.toEntity(userAssessmentRequest);
                userAssessment.setUser(user);
                updatedUserAssessments.add(userAssessment);
                existingUserAssessmentsMap.remove(userAssessment.getId());
            }
        });
    
        user.getUserAssessments().removeIf(a -> existingUserAssessmentsMap.containsKey(a.getId()));
    
        user.getUserAssessments().clear();
        user.getUserAssessments().addAll(updatedUserAssessments);
        
        return user;
    }

    public UserRequest toDto(User user) {
        UserRequest userRequest = new UserRequest(); 
        if(user.getId() != null){
            userRequest.setId(user.getId());
        }
        userRequest.setEmail(user.getEmail());
        userRequest.setPassword(user.getPassword());
        userRequest.setNickname(user.getNickname());
        userRequest.setUserLastLogin(user.getLastLogin());
        if (user.getMoods() != null) {
            List<MoodLogRequest> moods = user.getMoods().stream()
                    .map(userMoodLogServiceImpl::toDto)
                    .collect(Collectors.toList());
                    userRequest.setMoods(moods);
        }
        if (user.getUserAssessments() != null) {
            List<UserAssessmentRequest> userAssessments = user.getUserAssessments().stream()
                    .map(userAssessmentServiceImpl::toDto)
                    .collect(Collectors.toList());
                    userRequest.setUserAssessments(userAssessments);
        }
        return userRequest;
    }

    @Override
    public List<User> findAll() {
        log.info("Finding all users");
        try{
            return userRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all users : " + e.getMessage());
        }
    }

    @Override
    public User findById(UUID id) {
        log.info("Finding user with id: {}", id);
        try{
            return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding user : " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public void save(UserRequest userRequest) {
        log.info("Saving user: {}", userRequest);
        try{
            User user = toEntity(userRequest);

            for(UserMoodLog userMoodLog : user.getMoods()){
                log.info("userMoodLog: {}", userMoodLog.getId());
            }
            for(UserAssessment userAssessment : user.getUserAssessments()){
                log.info("userAssessment: {}", userAssessment.getId());
            }

            log.info("user: {}", user.getId());
            userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("Error saving user : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting user with id: {}", id);
        try{
            userRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting user : " + e.getMessage());
        }
    }
}
