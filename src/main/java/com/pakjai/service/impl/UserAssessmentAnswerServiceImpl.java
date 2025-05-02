package com.pakjai.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.UserAssessmentAnswerRequest;
import com.pakjai.entity.UserAssessmentAnswer;
import com.pakjai.repository.UserAssessmentAnswerRepository;
import com.pakjai.service.UserAssessmentService;
import com.pakjai.service.mapper.ObjectMapper;

@Service
public class UserAssessmentAnswerServiceImpl implements ObjectMapper<UserAssessmentAnswerRequest, UserAssessmentAnswer> {
    
    @Autowired
    private UserAssessmentAnswerRepository userAssessmentAnswerRepository;



    public UserAssessmentAnswer toEntity(UserAssessmentAnswerRequest request) {
        UserAssessmentAnswer answer = Optional.ofNullable(request.getId())
                .flatMap(userAssessmentAnswerRepository::findById)
                .orElseGet(UserAssessmentAnswer::new);
    
        answer.setAnswer(request.getAnswer());
        answer.setPoint(request.getPoint());
    
        return answer;
    }
    

    public UserAssessmentAnswerRequest toDto(UserAssessmentAnswer assessmentAnswer) {
        UserAssessmentAnswerRequest assessmentAnswerRequest = new UserAssessmentAnswerRequest();
        if(assessmentAnswer.getId() != null){
            assessmentAnswerRequest.setId(assessmentAnswer.getId());
        }
        assessmentAnswerRequest.setAnswer(assessmentAnswer.getAnswer());
        assessmentAnswerRequest.setPoint(assessmentAnswer.getPoint());
        if(assessmentAnswer.getUserAssessment() != null){
            assessmentAnswerRequest.setUserAssessmentId(assessmentAnswer.getUserAssessment().getId());
        }
        return assessmentAnswerRequest;
    }
}
