package com.pakjai.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.UserAssessmentAnswerRequest;
import com.pakjai.dto.UserAssessmentRequest;
import com.pakjai.entity.UserAssessment;
import com.pakjai.entity.UserAssessmentAnswer;
import com.pakjai.repository.UserAssessmentRepository;
import com.pakjai.service.UserAssessmentService;
import com.pakjai.service.mapper.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAssessmentServiceImpl implements UserAssessmentService, ObjectMapper<UserAssessmentRequest,UserAssessment> {

    @Autowired
    private UserAssessmentRepository userAssessmentRepository;
    
    @Autowired
    private UserAssessmentAnswerServiceImpl userAssessmentAnswerServiceImpl;

    public UserAssessment toEntity(UserAssessmentRequest request) {
        UserAssessment userAssessment = Optional.ofNullable(request.getId())
                .map(this::findById)
                .orElseGet(UserAssessment::new);
    
        userAssessment.setQuestion(request.getQuestion());
        userAssessment.setLevel(request.getLevel());
        userAssessment.setScore(request.getScore());
    
        // เตรียม answers collection ถ้ายังไม่มี
        if (userAssessment.getUserAnswers() == null) {
            userAssessment.setUserAnswers(new ArrayList<>());
        }
    
        // สร้าง Map ของ existing answers เพื่อการเปรียบเทียบ
        Map<UUID, UserAssessmentAnswer> existingAnswerMap = userAssessment.getUserAnswers().stream()
                .filter(a -> a.getId() != null)
                .collect(Collectors.toMap(UserAssessmentAnswer::getId, a -> a));
    
        List<UserAssessmentAnswer> updatedUserAnswers = new ArrayList<>();
    
        Optional.ofNullable(request.getUserAnswers()).ifPresent(userAnswerRequests -> {
            for (UserAssessmentAnswerRequest userAnswerRequest : userAnswerRequests) {
                UserAssessmentAnswer answer = userAssessmentAnswerServiceImpl.toEntity(userAnswerRequest);
                answer.setUserAssessment(userAssessment);
                updatedUserAnswers.add(answer);
                existingAnswerMap.remove(answer.getId());
            }
        });
    
        // ลบ answers ที่ไม่ได้อยู่ใน request
        userAssessment.getUserAnswers().removeIf(a -> existingAnswerMap.containsKey(a.getId()));
    
        // เพิ่มหรืออัปเดต answers
        userAssessment.getUserAnswers().clear();
        userAssessment.getUserAnswers().addAll(updatedUserAnswers);
    
        return userAssessment;
    }
    

    public UserAssessmentRequest toDto(UserAssessment userAssessment) {
        UserAssessmentRequest userAssessmentRequest = new UserAssessmentRequest(); 
        if(userAssessment.getId() != null){
            userAssessmentRequest.setId(userAssessment.getId());
        }
        userAssessmentRequest.setQuestion(userAssessment.getQuestion());
        userAssessmentRequest.setLevel(userAssessment.getLevel());
        userAssessmentRequest.setScore(userAssessment.getScore());
        if (userAssessment.getUserAnswers() != null) {
            List<UserAssessmentAnswerRequest> answers = userAssessment.getUserAnswers().stream()
                    .map(userAssessmentAnswerServiceImpl::toDto)
                    .collect(Collectors.toList());
            userAssessmentRequest.setUserAnswers(answers);
        }
        return userAssessmentRequest;
    }

    @Override
    public List<UserAssessment> findAll() {
        log.info("Finding all assessments");
        try{
            return userAssessmentRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all assessments : " + e.getMessage());
        }
    }

    @Override
    public UserAssessment findById(UUID id) {   
        log.info("Finding userAssessment with id: {}", id);
        try{
            return userAssessmentRepository.findById(id).orElseThrow(() -> new RuntimeException("UserAssessment not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding userAssessment : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void save(UserAssessmentRequest userAssessmentRequest) {
        log.info("Saving userAssessment: {}", userAssessmentRequest);
        try{
            UserAssessment userAssessment = toEntity(userAssessmentRequest);
            
            log.info("userAssessment: {}", userAssessment.getId());
            for(UserAssessmentAnswer answer : userAssessment.getUserAnswers()){
                log.info("answer: {}", answer.getId()); 
            }
            userAssessmentRepository.save(userAssessment);
        }catch(Exception e){
            throw new RuntimeException("Error saving userAssessment : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting userAssessment with id: {}", id);
        try{
            userAssessmentRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting userAssessment : " + e.getMessage());
        }
    }

}
