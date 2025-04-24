package com.pakjai.entity.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseMasterEntity extends BaseEntity{
    String code;

    String name;
}
