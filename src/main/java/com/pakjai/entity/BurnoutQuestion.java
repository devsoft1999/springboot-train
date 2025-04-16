package com.pakjai.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import com.pakjai.entity.model.BaseEntity;

@Data
@Entity
@Table(name = "burnout_questions")
public class BurnoutQuestion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionKey;
    private String textTh;
    private String textEn;
    private Integer orderNo;
    private String category;
    private Boolean active = true;
}