package com.cc.liquibase.service;

import com.cc.liquibase.controller.MarketItems;
import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.entity.ItemEntity;
import com.cc.liquibase.entity.ItemTypeEntity;
import com.cc.liquibase.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@WebMvcTest(ItemServiceImpl.class)//With this liquibase is not loaded
//@RunWith(SpringRunner.class)//In JUnit 5 the runner is not required
class ItemServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemsService;//Para la annotations InjectMocks is necessary the implemented class not the interface

    //In case we use @WebMvcTest we need change the attributes as follow
//    @MockBean
//    ItemRepository itemRepository;
//
//    @Autowired
//    private ItemServiceImpl itemsService;

    @Test
    void createItem() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void getItems() {
        List<ItemEntity> itemDtos = Arrays.asList(
                ItemEntity.builder()
                        .name("manzana")
                        .cost(10.2)
                        .type_product(
                                ItemTypeEntity.builder()
                                        .id(1)
                                        .name("fruta")
                                        .build()
                        ).build(),
                ItemEntity.builder()
                        .name("Perejil")
                        .cost(20.5)
                        .type_product(
                                ItemTypeEntity.builder()
                                        .id(2)
                                        .name("Verdura")
                                        .build()
                        ).build()
        );
        Mockito.doReturn(itemDtos).when(itemRepository).findAll();

        GeneralResponse<List<ItemDto>> items = itemsService.getItems();
        assertEquals(items.getStatus(), HttpStatus.OK);
        assertEquals(items.getBody().size(),2);
    }

    @Test
    void getItem() {
    }

    @Test
    void deleteItem() {
    }
}