package com.cc.liquibase.repository;

import com.cc.liquibase.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {
    public List<ItemEntity> findByName(String name);
}
