package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
