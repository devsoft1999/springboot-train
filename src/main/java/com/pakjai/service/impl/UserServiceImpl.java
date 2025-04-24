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
import com.pakjai.dto.UserRequest;
import com.pakjai.entity.Assessment;
import com.pakjai.entity.User;
import com.pakjai.entity.UserMoodLog;
import com.pakjai.repository.UserRepository;
import com.pakjai.service.UserService;
import com.pakjai.service.mapper.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService, ObjectMapper<UserRequest, User> {
    
    @Autowired
    private UserRepository userRepository;

    public User toEntity(UserRequest request) {
        User user = Optional.ofNullable(request.getId())
            .map(this::findById)
            .orElseGet(User::new);

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setLastLogin(request.getUserLastLogin());

        // if (user.getMoods() == null) {
        //     user.setMoods(new ArrayList<>());
        // }

        // Map<UUID, UserMoodLog> existingMoodMap = user.getMoods().stream()
        //     .filter(a -> a.getId() != null)
        //     .collect(Collectors.toMap(UserMoodLog::getId, a -> a));

        // List<UserMoodLog> updatedMoods = new ArrayList<>();

        // Optional.ofNullable(request.getMoods()).ifPresent(moodRequests -> {
        //     for (MoodLogRequest moodRequest : moodRequests) {}
        // });
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
