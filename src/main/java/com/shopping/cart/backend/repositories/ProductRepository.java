package com.shopping.cart.backend.repositories;

import java.util.List;

import com.shopping.cart.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainsIgnoreCaseAndType(String name, String type);

    List<Product> findByNameContainsIgnoreCase(String name);

    List<Product> findByType(String type);
}
