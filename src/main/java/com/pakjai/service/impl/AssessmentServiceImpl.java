package com.pakjai.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pakjai.dto.AssessmentAnswerRequest;
import com.pakjai.dto.AssessmentRequest;
import com.pakjai.entity.Assessment;
import com.pakjai.entity.AssessmentAnswer;
import com.pakjai.repository.AssessmentRepository;
import com.pakjai.service.AssessmentService;
import com.pakjai.service.mapper.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AssessmentServiceImpl implements AssessmentService, ObjectMapper<AssessmentRequest,Assessment> {

    @Autowired
    private AssessmentRepository assessmentRepository;
    
    @Autowired
    private AssessmentServiceAnswerImpl assessmentServiceAnswerImpl;

    public Assessment toEntity(AssessmentRequest request) {
        Assessment assessment = Optional.ofNullable(request.getId())
                .map(this::findById)
                .orElseGet(Assessment::new);
    
        assessment.setSeq(request.getSeq());
        assessment.setQuestion(request.getQuestion());
        assessment.setType(request.getType());
        assessment.setStatus(request.getStatus());
        List<AssessmentAnswer> answers = assessment.getAnswers();
        if(answers!= null){
           log.info("answers: {}", answers.size());
            answers.clear();
        }

        Optional.ofNullable(request.getAnswers())
                .ifPresent(answerRequests -> {
                    List<AssessmentAnswer> answersFromRequest = answerRequests.stream()
                            .map(assessmentServiceAnswerImpl::toEntity)
                            .peek(answer -> answer.setAssessment(assessment))
                            .collect(Collectors.toList());
    
                    assessment.setAnswers(answersFromRequest);
                });
    
        return assessment;
    }
    

    public AssessmentRequest toDto(Assessment assessment) {
        AssessmentRequest assessmentRequest = new AssessmentRequest(); 
        if(assessment.getId() != null){
            assessmentRequest.setId(assessment.getId());
        }
        assessmentRequest.setSeq(assessment.getSeq());
        assessmentRequest.setQuestion(assessment.getQuestion());
        assessmentRequest.setType(assessment.getType());
        assessmentRequest.setStatus(assessment.getStatus());
        if (assessment.getAnswers() != null) {
            List<AssessmentAnswerRequest> answers = assessment.getAnswers().stream()
                    .map(assessmentServiceAnswerImpl::toDto)
                    .collect(Collectors.toList());
            assessmentRequest.setAnswers(answers);
        }
        return assessmentRequest;
    }

    @Override
    public List<Assessment> findAll() {
        log.info("Finding all assessments");
        try{
            return assessmentRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error finding all assessments : " + e.getMessage());
        }
    }

    @Override
    public Assessment findById(UUID id) {   
        log.info("Finding assessment with id: {}", id);
        try{
            return assessmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Assessment not found"));
        }catch(Exception e){
            throw new RuntimeException("Error finding assessment : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void save(AssessmentRequest assessmentRequest) {
        log.info("Saving assessment: {}", assessmentRequest);
        try{
            Assessment assessment = toEntity(assessmentRequest);
            
            log.info("assessment: {}", assessment.getId());
            for(AssessmentAnswer answer : assessment.getAnswers()){
                log.info("answer: {}", answer.getId()); 
            }
            assessmentRepository.save(assessment);
        }catch(Exception e){
            throw new RuntimeException("Error saving assessment : " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        log.info("Deleting assessment with id: {}", id);
        try{
            assessmentRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error deleting assessment : " + e.getMessage());
        }
    }

}
