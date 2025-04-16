
package com.pakjai.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import com.pakjai.entity.model.BaseEntity;

@Data
@Entity
@Table(name = "burnout_assessments")
public class BurnoutAssessment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer scoreTotal;
    private String level;

    @Column(columnDefinition = "jsonb")
    private String answersJson;

}