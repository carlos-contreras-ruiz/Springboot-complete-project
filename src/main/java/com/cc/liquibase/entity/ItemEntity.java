package com.cc.liquibase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "cost" ,nullable = false)
    private Double cost;

    @ManyToOne
    @JoinColumn(name="type_product_id")
    private ItemTypeEntity type_product;
}
