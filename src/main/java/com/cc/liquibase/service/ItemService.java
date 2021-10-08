package com.cc.liquibase.service;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;

import java.util.List;

public interface ItemService {
    public GeneralResponse<ItemDto> createItem(ItemDto item);
    public GeneralResponse<ItemDto> updateItem(int id,ItemDto item);
    public GeneralResponse<List<ItemDto>> getItems();
    public GeneralResponse<ItemDto> getItem(int id);
    public GeneralResponse<String> deleteItem(int id);
}
