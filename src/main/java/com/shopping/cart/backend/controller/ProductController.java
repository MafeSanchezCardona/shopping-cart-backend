package com.shopping.cart.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.shopping.cart.backend.dto.ProductDto;
import com.shopping.cart.backend.entities.Product;
import com.shopping.cart.backend.services.ProductService;
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
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService) {
        this.productService = productService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<String> save(@Valid @RequestBody ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        productService.save(modelMapper.map(productDto, Product.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> findAll(@RequestParam Optional<String> productName,
                                          @RequestParam Optional<String> type) {
        List<Product> products = productService.findAll(productName, type);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(
            products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()),
            HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modelMapper.map(product, ProductDto.class), HttpStatus.OK);
    }
}
