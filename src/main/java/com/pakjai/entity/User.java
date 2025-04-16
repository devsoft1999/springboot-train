package com.pakjai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
import com.pakjai.entity.model.BaseEntity;

@Data
@Entity
@Table(name = "users")
public class User  extends BaseEntity{
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String passwordHash;
    private String nickname;
    private LocalDateTime lastLogin;


    @PrePersist
    public void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}