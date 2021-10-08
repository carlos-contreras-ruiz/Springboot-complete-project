package com.cc.liquibase.controller;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class MarketItems {

    @Autowired
    ItemService itemsService;

    @GetMapping(value = "/items", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralResponse<List<ItemDto>>> getItems(){
        GeneralResponse<List<ItemDto>> response = itemsService.getItems();
        return new ResponseEntity<GeneralResponse<List<ItemDto>>>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping(value = "/items/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralResponse<ItemDto>> getItemById(@PathVariable int id){
        GeneralResponse<ItemDto> response = itemsService.getItem(id);
        return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @DeleteMapping(value = "/items/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralResponse<String>> deleteItems(@PathVariable int id){
        GeneralResponse<String> response = itemsService.deleteItem(id);
        return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PutMapping(value = "/items/{id}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralResponse<ItemDto>> updateItem(@PathVariable int id,  @RequestBody ItemDto request){
        GeneralResponse<ItemDto> response = itemsService.updateItem(id,request);
        return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @PostMapping(value = "/items", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeneralResponse<ItemDto>> createItem(@RequestBody ItemDto request){
        GeneralResponse<ItemDto> response = itemsService.createItem(request);
        return new ResponseEntity<>(response, response.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
