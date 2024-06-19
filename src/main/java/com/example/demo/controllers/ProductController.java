package com.example.demo.controllers;

import Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    // DI = Dependency injection
    @Autowired
    private ProductRepository repository;
    @GetMapping("")
    // This request is: http//localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
    }
}
