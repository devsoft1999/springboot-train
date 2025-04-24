package com.pakjai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pakjai.entity.model.BaseEntity;

@Data
@Entity
@Table(name = "su_users")
public class User  extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_email")
    private String email;

    @JsonIgnore
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_nickname")
    private String nickname;

    @Column(name = "user_last_login")
    private LocalDateTime lastLogin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserMoodLog> moods;

}