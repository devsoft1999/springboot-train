package com.pakjai.entity;

import com.pakjai.entity.model.BaseMasterEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "burnout_advice")
public class BurnoutAdvice extends BaseMasterEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
    @Column(name = "level")
    private String level;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;

}