package com.cc.liquibase.service;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.entity.ItemEntity;
import com.cc.liquibase.entity.ItemTypeEntity;
import com.cc.liquibase.repository.ItemRepository;
import com.cc.liquibase.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    /*@Override
    public GeneralResponse<ItemDto> createItem(ItemDto item) {
        ItemTypeEntity type = new ItemTypeEntity();
        type.setId(item.getTypeProduct().getId());
        type.setName(item.getTypeProduct().getName());
        ItemEntity save = itemRepository.save(new ItemEntity(item.getName(), item.getCost(), type));

        ItemTypeDto typeDto = new ItemTypeDto(save.getType_product().getId(),save.getType_product().getName());

        ItemDto itemResp = new ItemDto(save.getId(),save.getName(),save.getCost(),typeDto);

        GeneralResponse<ItemDto> response = new GeneralResponse<>();
        response.setHttpCode(HttpStatus.OK.toString());
        response.setStatus(HttpStatus.OK);
        response.setBody(itemResp);
        response.setError(false);

        return response;
    }*/

    @Override
    public GeneralResponse<ItemDto> createItem(ItemDto item) {

        ItemEntity saveItem = Utils.itemDtoToEntity(item);


        ItemEntity saveResponse = itemRepository.save(saveItem);

        ItemDto itemResp = Utils.itemEntityToDto(saveResponse);

        GeneralResponse<ItemDto> response = new GeneralResponse<>();
        response.setHttpCode(HttpStatus.OK.toString());
        response.setStatus(HttpStatus.OK);
        response.setBody(itemResp);
        response.setError(false);

        return response;
    }

    @Override
    public GeneralResponse<ItemDto> updateItem(int id,ItemDto item) {
        Optional<ItemEntity> resp = itemRepository.findById(id);
        if (resp.isPresent()){
            ItemEntity updateValue = resp.get();
            updateValue.setName(item.getName());
            updateValue.setCost(item.getCost());
            updateValue.getType_product().setName(item.getTypeProduct().getName());

            ItemEntity saveResponse = itemRepository.save(updateValue);
            return Utils.responseBuilder(Utils.itemEntityToDto(saveResponse),HttpStatus.OK.toString(),HttpStatus.OK,false);
        }else {
            return Utils.responseBuilder(null,HttpStatus.NOT_FOUND.toString(),HttpStatus.NOT_FOUND,true);
        }
    }

    @Override
    public GeneralResponse<List<ItemDto>> getItems() {
        List<ItemEntity> responseBd = itemRepository.findAll();

        List<ItemDto> itemDtoList = Utils.itemListEntityToDto(responseBd);

        GeneralResponse<List<ItemDto>> response = new GeneralResponse<>();
        response.setHttpCode(HttpStatus.OK.toString());
        response.setStatus(HttpStatus.OK);
        response.setBody(itemDtoList);
        response.setError(false);

        return response;
    }

    @Override
    public GeneralResponse<ItemDto> getItem(int id) {
        Optional<ItemEntity> resp = itemRepository.findById(id);
        if (resp.isPresent()){
            return Utils.responseBuilder(Utils.itemEntityToDto(resp.get()),
                    HttpStatus.OK.toString(),
                    HttpStatus.OK,
                    false);
        }else{
            return Utils.responseBuilder(null,HttpStatus.NOT_FOUND.toString(),HttpStatus.NOT_FOUND,true);
        }
    }

    @Override
    public GeneralResponse<String> deleteItem(int id) {
        itemRepository.deleteById(id);
        return Utils.responseBuilder(null,HttpStatus.OK.toString(),HttpStatus.OK,false);
    }
}
