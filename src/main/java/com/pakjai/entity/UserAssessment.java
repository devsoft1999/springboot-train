package com.pakjai.entity;

import java.util.List;
import java.util.UUID;

import com.pakjai.entity.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "su_user_assessments")
public class UserAssessment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "score")
    private Integer score;

    @Column(name = "level")
    private String level;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "userAssessment", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserAssessmentAnswer> userAnswers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

}
