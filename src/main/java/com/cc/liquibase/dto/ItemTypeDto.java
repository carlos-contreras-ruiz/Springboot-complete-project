package com.cc.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemTypeDto {
    private int id;
    private String name;
}
