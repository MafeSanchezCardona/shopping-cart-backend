package com.shopping.cart.backend.services;

import java.util.List;
import java.util.Optional;

import com.shopping.cart.backend.entities.Product;
import com.shopping.cart.backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product Service
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Method to save the product entity
     *
     * @param product
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(final Product product) {
        productRepository.save(product);
    }

    /**
     * Method to list of products (Filter by product name and type)
     *
     * @param productName
     * @param type
     * @return List of product
     */
    @Transactional(readOnly = true)
    public List<Product> findAll(final Optional<String> productName, final Optional<String> type) {
        if (productName.isPresent() && !productName.get().isBlank() && type.isPresent() && !type.get().isBlank()) {
            return productRepository.findByNameContainsIgnoreCaseAndType(productName.get(), type.get());
        } else if (productName.isPresent() && !productName.get().isBlank()) {
            return productRepository.findByNameContainsIgnoreCase(productName.get());
        } else if (type.isPresent() && !type.get().isBlank()) {
            return productRepository.findByType(type.get());
        }
        return productRepository.findAll();
    }

    /**
     * Method to find specific product
     *
     * @param id
     * @return Product
     */
    @Transactional(readOnly = true)
    public Product findById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
