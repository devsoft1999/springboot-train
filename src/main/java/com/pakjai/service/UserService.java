package com.pakjai.service;

import java.util.List;
import java.util.UUID;

import com.pakjai.dto.UserRequest;
import com.pakjai.entity.User;

public interface UserService {
    
    List<User> findAll();

    User findById(UUID id);

    void save(UserRequest userRequest);

    void delete(UUID id);
}
