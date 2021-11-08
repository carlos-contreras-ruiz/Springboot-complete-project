package com.cc.liquibase.repository;

import com.cc.liquibase.entity.ItemEntity;
import com.cc.liquibase.entity.ItemTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    private ItemEntity entitySaved;

    @BeforeEach
    public void insertRecords(){
        List<ItemEntity> itemEntity = listItems();
        entitySaved = itemRepository.save(itemEntity.get(0));
        itemRepository.save(itemEntity.get(1));
    }

    @Test
    public void findById() {

        List<ItemEntity> manzana = itemRepository.findByName("Manzana");

        assertThat(manzana.get(0).getName()).isEqualTo("Manzana");

        Optional<ItemEntity> byId = itemRepository.findById(entitySaved.getId());

        if (byId.isPresent()) {
            assertThat(byId.get().getName()).isEqualTo("Manzana");
        } else {
            //Assert.isTrue(false);
            assertTrue(false);
        }

    }


    @Test
    public void findAll() {

        List<ItemEntity> all = itemRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
    }

    private List<ItemEntity> listItems() {
        return Arrays.asList(ItemEntity.builder()
                        .name("Manzana")
                        .cost(10.20)
                        .type_product(
                                ItemTypeEntity.builder()
                                        .id(1)
                                        .name("fruta")
                                        .build()
                        ).build(),
                ItemEntity.builder()
                        .name("perejil")
                        .cost(2.4)
                        .type_product(
                                ItemTypeEntity.builder()
                                        .id(2)
                                        .name("Verdura")
                                        .build()
                        ).build());
    }
}
