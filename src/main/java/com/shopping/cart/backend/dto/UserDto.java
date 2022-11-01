package com.shopping.cart.backend.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userId;

    @NotEmpty(message = "The login name must not be empty")
    private String loginName;

    @NotEmpty(message = "The password must not be empty")
    private String password;
}
