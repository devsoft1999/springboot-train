package com.pakjai.entity;

import com.pakjai.entity.model.BaseEntity;
import com.pakjai.entity.model.BaseMasterEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserParameter extends BaseMasterEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String passwordHash;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_lastLogin")
    private LocalDateTime lastLogin;


//
//    @PrePersist
//    public void ensureId() {
//        if (id == null) {
//            id = UUID.randomUUID();
//        }
//    }
}