package com.pakjai.service;

import com.pakjai.dto.UserBurnoutAdviceLogRequest;
import com.pakjai.entity.UserBurnoutAdviceLog;

import java.util.List;
import java.util.UUID;

public interface UserBurnoutAdviceLogService {

    List<UserBurnoutAdviceLog> findAll();

    UserBurnoutAdviceLog findById(UUID id);

    void save(UserBurnoutAdviceLogRequest userBurnoutAdviceLogRequest);

    void delete(UUID id);

}
