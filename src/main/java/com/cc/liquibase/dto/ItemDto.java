package com.cc.liquibase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    public int id;
    public String name;
    public double cost;
    public ItemTypeDto typeProduct;
}
