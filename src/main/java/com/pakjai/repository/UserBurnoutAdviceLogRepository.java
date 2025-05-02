package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.UserBurnoutAdviceLog;

public interface UserBurnoutAdviceLogRepository extends JpaRepository<UserBurnoutAdviceLog, UUID> {
   
}
