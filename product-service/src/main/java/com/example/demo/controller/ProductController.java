package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.document.ProductDocument;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private   ProductService productService;


    @PostMapping
    public ProductDocument saveProduct(@RequestBody ProductDocument product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<ProductDocument> getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }
}