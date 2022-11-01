package com.shopping.cart.backend.services;

import java.util.List;

import com.shopping.cart.backend.dto.BasketDto;
import com.shopping.cart.backend.entities.Basket;
import com.shopping.cart.backend.entities.Product;
import com.shopping.cart.backend.entities.User;
import com.shopping.cart.backend.repositories.BasketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Basket Service
 */
@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;

    public BasketService(BasketRepository basketRepository, UserService userService, ProductService productService) {
        this.basketRepository = basketRepository;
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * Method to save the basket entity
     * It is validated that the user and product exist
     *
     * @param basketDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(final BasketDto basketDto) {
        Product product = productService.findById(basketDto.getProductId());

        if (product == null) {
            throw new IllegalArgumentException("the product does not exist");
        }

        User user = userService.findById(basketDto.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("the user does not exist");
        }

        Basket basket = new Basket();
        basket.setProduct(product);
        basket.setUser(user);
        basket.setQuantity(basketDto.getQuantity());
        basketRepository.save(basket);
    }

    /**
     * Method to list items
     *
     * @return List of basket
     */
    @Transactional(readOnly = true)
    public List<Basket> findAll() {
        return basketRepository.findAll();
    }

    /**
     * Method to find specific item in cart
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Basket findById(final Long id) {
        return basketRepository.findById(id).orElse(null);
    }

    /**
     * Method to list items in cart for a specific user
     *
     * @param userId
     * @return List of basket
     */
    @Transactional(readOnly = true)
    public List<Basket> findByUserId(final Long userId) {
        User user = userService.findById(userId);
        return basketRepository.findAllByUser(user);
    }
}
