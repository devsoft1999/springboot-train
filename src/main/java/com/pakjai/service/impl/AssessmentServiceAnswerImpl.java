package com.pakjai.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.AssessmentAnswerRequest;
import com.pakjai.entity.AssessmentAnswer;
import com.pakjai.repository.AssessmentAnswerRepository;
import com.pakjai.service.AssessmentService;
import com.pakjai.service.mapper.ObjectMapper;

@Service
public class AssessmentServiceAnswerImpl implements ObjectMapper< AssessmentAnswerRequest,AssessmentAnswer> {
    
    @Autowired
    private AssessmentAnswerRepository assessmentAnswerRepository;



    public AssessmentAnswer toEntity(AssessmentAnswerRequest request) {
        AssessmentAnswer answer = Optional.ofNullable(request.getId())
                .flatMap(assessmentAnswerRepository::findById)
                .orElseGet(AssessmentAnswer::new);
    
        answer.setSeq(request.getSeq());
        answer.setAnswer(request.getAnswer());
        answer.setPoint(request.getPoint());
    
        return answer;
    }
    

    public AssessmentAnswerRequest toDto(AssessmentAnswer assessmentAnswer) {
        AssessmentAnswerRequest assessmentAnswerRequest = new AssessmentAnswerRequest();
        if(assessmentAnswer.getId() != null){
            assessmentAnswerRequest.setId(assessmentAnswer.getId());
        }
        assessmentAnswerRequest.setSeq(assessmentAnswer.getSeq());
        assessmentAnswerRequest.setAnswer(assessmentAnswer.getAnswer());
        assessmentAnswerRequest.setPoint(assessmentAnswer.getPoint());
        if(assessmentAnswer.getAssessment() != null){
            assessmentAnswerRequest.setAssessmentId(assessmentAnswer.getAssessment().getId());
        }
        return assessmentAnswerRequest;
    }
}
