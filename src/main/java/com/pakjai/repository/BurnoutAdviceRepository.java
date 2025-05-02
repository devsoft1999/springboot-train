package com.pakjai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pakjai.entity.BurnoutAdvice;

public interface BurnoutAdviceRepository extends JpaRepository<BurnoutAdvice, UUID> {
   
}
