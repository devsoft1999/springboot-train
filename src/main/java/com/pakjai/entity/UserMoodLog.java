package com.pakjai.entity;

import com.pakjai.entity.model.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "userMoodLog")
public class UserMoodLog extends BaseMasterEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(name = "mood")
    private String mood;
    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
//
//    @PrePersist
//    public void ensureId() {
//        if (id == null) {
//            id = UUID.randomUUID();
//        }
//    }
}