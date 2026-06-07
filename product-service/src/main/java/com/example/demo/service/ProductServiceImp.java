package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.document.ProductDocument;
import com.example.demo.repository.ProductRepository;

public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
    public ProductDocument saveProduct(ProductDocument product) {

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }
    
    public Optional<ProductDocument> getProductById(String id) {
        return productRepository.findById(id);
    }
}
