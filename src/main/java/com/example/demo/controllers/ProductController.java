package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    // DI = Dependency injection
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    // This request is: http//localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    // This request is: http//localhost:8080/api/v1/Products/{id}
    // Let's return an object with: data, message, status
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query product succesfully", foundProduct)
            )
        :
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "Can not find product with id = " + id, "")
        );
    }

    // insert with post method
    // PostMapping("/insert")
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        // validate: 2 product must not have the same name!
        List<Product> foundProducts = productRepository.findByProductName(newProduct.getProductName().trim());
        if (!foundProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "product name already taken", "")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "insert success", productRepository.save(newProduct))
        );
    }

    // update / upsert

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = productRepository.findById(id).map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setPrice(newProduct.getPrice());
            product.setYear(newProduct.getYear());
            product.setUrl(newProduct.getUrl());
            return productRepository.save(product);
        }).orElseGet(()-> {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "update Product successfully", updateProduct)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {

        boolean productExist = productRepository.existsById(id);

        if (productExist) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "delete successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("false", "product not found", "")
        );

    }
}
