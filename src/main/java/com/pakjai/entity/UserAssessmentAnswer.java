package com.pakjai.entity;

import java.util.UUID;

import com.pakjai.entity.model.BaseEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "su_user_assessment_answers")
public class UserAssessmentAnswer extends BaseEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_assessment_id")
    @JsonBackReference
    private UserAssessment userAssessment;

    @Column(name = "answer")
    private String answer;

    @Column(name = "point")
    private Integer point;

}
