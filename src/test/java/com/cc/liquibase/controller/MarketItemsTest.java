package com.cc.liquibase.controller;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.dto.ItemTypeDto;
import com.cc.liquibase.service.ItemService;
import com.cc.liquibase.service.ItemServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarketItemsTest {

    @Mock
    private ItemService itemsService;//For the mock annotations we can use the interface instead the implementation

    @InjectMocks
    private MarketItems marketItems;

    @Before
    public void init() {
        //MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Get all items")
    @Test
    void getItems() {

        List<ItemDto> itemDtos = Arrays.asList(ItemDto.builder()
                        .id(1)
                        .name("Manzana")
                        .cost(10.20)
                        .typeProduct(
                                ItemTypeDto.builder()
                                        .id(1)
                                        .name("fruta")
                                        .build()
                        ).build(),
                ItemDto.builder()
                        .id(2)
                        .name("perejil")
                        .cost(2.4)
                        .typeProduct(
                                ItemTypeDto.builder()
                                        .id(2)
                                        .name("Verdura")
                                        .build()
                        ).build());

        GeneralResponse<List<ItemDto>> response = new GeneralResponse<>();
        response.setHttpCode(HttpStatus.OK.toString());
        response.setStatus(HttpStatus.OK);
        response.setBody(itemDtos);
        response.setError(false);

        Mockito.doReturn(response).when(itemsService).getItems();
        //Mockito.when(itemsService.getItems()).thenReturn(response);

        ResponseEntity<GeneralResponse<List<ItemDto>>> itemsEntity = marketItems.getItems();
        final GeneralResponse<List<ItemDto>> items = itemsEntity.getBody();

        assertEquals(items.getStatus(),HttpStatus.OK);
        assertEquals(items.getBody().size(),2);

    }

    @Test
    void getItemById() {
    }

    @Test
    void deleteItems() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void createItem() {
    }
}