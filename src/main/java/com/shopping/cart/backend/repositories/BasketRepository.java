package com.shopping.cart.backend.repositories;

import java.util.List;

import com.shopping.cart.backend.entities.Basket;
import com.shopping.cart.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByUser(User user);
}
