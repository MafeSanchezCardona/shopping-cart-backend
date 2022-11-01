package com.shopping.cart.backend.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty(message = "The name must not be empty")
    private String name;

    @NotEmpty(message = "The type must not be empty")
    private String type;

    private String description;
}

