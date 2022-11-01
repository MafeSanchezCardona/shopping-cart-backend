package com.shopping.cart.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"loginName"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", updatable = false, nullable = false, unique = true)
    private Long userId;

    @NotEmpty(message = "The login name must not be empty")
    @Column(name = "loginName", columnDefinition = "varchar2(20)")
    private String loginName;

    @NotEmpty(message = "The password must not be empty")
    @Column(name = "password", columnDefinition = "varchar2(20)")
    private String password;

}
