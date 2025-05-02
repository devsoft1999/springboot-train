package com.pakjai.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;
//
//    @PrePersist
//    public void ensureId() {
//        if (id == null) {
//            id = UUID.randomUUID();
//        }
//    }
}