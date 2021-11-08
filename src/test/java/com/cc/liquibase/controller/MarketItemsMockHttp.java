package com.cc.liquibase.controller;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.dto.ItemTypeDto;
import com.cc.liquibase.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest //When we use WebMvcTest we dont use this annotation
//@AutoConfigureMockMvc
@WebMvcTest(MarketItems.class)//Levanta una version mas ligera del servicio
public class MarketItemsMockHttp {

    @MockBean//This is required on WebMvcTest annotation
    private ItemService itemsService;//For the mock annotations we can use the interface instead the implementation

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getItems() throws Exception {
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

        //Mockito.doReturn(response).when(itemsService).getItems();
        when(itemsService.getItems()).thenReturn(response);

        this.mockMvc.perform(get("/api/v1/items")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Verdura")
                        )
                );

        verify(itemsService).getItems();
    }
}
