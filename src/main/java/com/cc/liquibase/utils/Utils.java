package com.cc.liquibase.utils;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.dto.ItemTypeDto;
import com.cc.liquibase.entity.ItemEntity;
import com.cc.liquibase.entity.ItemTypeEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static ItemEntity itemDtoToEntity(ItemDto item){
        return ItemEntity.builder()
                .name(item.getName())
                .cost(item.getCost())
                .type_product(
                        ItemTypeEntity.builder()
                                .id(item.getTypeProduct().getId())
                                .name(item.getTypeProduct().getName())
                                .build()
                ).build();
    }

    public static ItemDto itemEntityToDto(ItemEntity item){
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .cost(item.getCost())
                .typeProduct(
                        ItemTypeDto.builder()
                                .id(item.getType_product().getId())
                                .name(item.getType_product().getName())
                                .build()
                ).build();
    }

    public static List<ItemDto> itemListEntityToDto(List<ItemEntity> entityList){
        return entityList.stream()
                //.map((entity) -> Utils.itemEntityToDto(entity))
                .map(Utils::itemEntityToDto)
        .collect(Collectors.toList());
    }

    public static <T> GeneralResponse<T> responseBuilder(T body, String status,HttpStatus httpStatus,boolean error){
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setHttpCode(status);
        response.setStatus(httpStatus);
        response.setBody(body);
        response.setError(error);

        return response;
    }
}
