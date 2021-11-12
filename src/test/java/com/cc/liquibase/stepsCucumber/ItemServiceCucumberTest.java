package com.cc.liquibase.stepsCucumber;

import com.cc.liquibase.dto.GeneralResponse;
import com.cc.liquibase.dto.ItemDto;
import com.cc.liquibase.entity.ItemEntity;
import com.cc.liquibase.entity.ItemTypeEntity;
import com.cc.liquibase.repository.ItemRepository;
import com.cc.liquibase.service.ItemService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ItemServiceCucumberTest{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemsService;

    private GeneralResponse<List<ItemDto>> items;

    @Given("Create record in the db")
    public void create_record_in_the_db() {
        List<ItemEntity> itemEntity = Arrays.asList(
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
        itemEntity.stream().forEach(entity->{
            itemRepository.save(entity);
        });

    }

    @When("Bring all the records in the db")
    public void bring_all_the_records_in_the_db() {
        this.items = itemsService.getItems();
    }

    @Then("The status must be OK")
    public void the_status_must_be_ok() {
        assertEquals(this.items.getStatus(), HttpStatus.OK);
    }

    @Then("The size must be {int}")
    public void the_size_must_be(int size) {
        assertEquals(this.items.getBody().size(),size);
    }
}
