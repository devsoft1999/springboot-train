package com.pakjai.entity;

import com.pakjai.entity.model.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_burnout_advice_log")
public class UserBurnoutAdviceLog extends BaseMasterEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
//
//    @Column(name = "user_mood")
//    private String mood;
//    @Column(name = "user_note")
//    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "burnout_advice_id")
    BurnoutAdvice burnoutAdvice ;

}