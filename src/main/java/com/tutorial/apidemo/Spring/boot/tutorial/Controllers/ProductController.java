package com.tutorial.apidemo.Spring.boot.tutorial.Controllers;

import Repositories.ProductRepository;
import com.tutorial.apidemo.Spring.boot.tutorial.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    // this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id")
    // Let's return an object with: data, message, status
    Product findProduct(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("Can not find product with id = " + id));
    }
}
