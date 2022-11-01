package com.shopping.cart.backend.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.shopping.cart.backend.dto.BasketDto;
import com.shopping.cart.backend.entities.Basket;
import com.shopping.cart.backend.services.BasketService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
@RestController
@RequestMapping("/basket")
public class BasketController {
    private final BasketService basketService;
    private final ModelMapper modelMapper;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody BasketDto basketDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            basketService.save(basketDto);
        } catch (Exception ex) {
            return new ResponseEntity<>("This product already exists in the cart", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<Basket> basket = basketService.findAll();

        if (basket.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
            basket.stream().map(item -> modelMapper.map(item, BasketDto.class)).collect(Collectors.toList()),
            HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Basket item = basketService.findById(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modelMapper.map(item, BasketDto.class), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> findByUserId(@RequestParam Long userId) {
        List<Basket> basket = basketService.findByUserId(userId);

        if (basket.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(
            basket.stream().map(item -> modelMapper.map(item, BasketDto.class)).collect(Collectors.toList()),
            HttpStatus.OK);
    }
}
