package com.pakjai.entity;

import java.util.UUID;

import com.pakjai.entity.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "su_assessment_answers")
public class AssessmentAnswer extends BaseEntity {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "answer")
    private String answer;

    @Column(name = "point")
    private Integer point;

}
