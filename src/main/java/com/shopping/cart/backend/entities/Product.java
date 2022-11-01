package com.shopping.cart.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @NotEmpty(message = "The name must not be empty")
    @Column(name = "name", columnDefinition = "varchar2(32)")
    private String name;

    @NotEmpty(message = "The type must not be empty")
    @Column(name = "type", columnDefinition = "varchar2(8)")
    private String type;

    @Column(name = "description", columnDefinition = "varchar2(100)")
    private String description;
}
