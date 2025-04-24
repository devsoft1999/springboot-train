package com.pakjai.service.mapper;


import com.pakjai.dto.AssessmentRequest;
import com.pakjai.entity.Assessment;

public interface ObjectMapper<R,T > {
    T toEntity(R object);
    R toDto(T object);
}
