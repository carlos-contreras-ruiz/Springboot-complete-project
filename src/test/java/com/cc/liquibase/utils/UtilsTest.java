package com.cc.liquibase.utils;

import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.dto.ItemTypeDto;
import com.cc.liquibase.entity.ItemEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void itemDtoToEntity() {
        ItemDto itemDto = ItemDto.builder()
                .id(1)
                .name("Manzana")
                .cost(10.20)
                .typeProduct(
                        ItemTypeDto.builder()
                                .id(1)
                                .name("fruta")
                                .build()
                ).build();
        ItemEntity itemEntity = Utils.itemDtoToEntity(itemDto);
        assertEquals(itemDto.getName(),itemEntity.getName());
        assertEquals(itemEntity.getClass(),ItemEntity.class);
    }

    @Test
    void itemEntityToDto() {
    }

    @Test
    void itemListEntityToDto() {
    }

    @Test
    void responseBuilder() {
    }
}