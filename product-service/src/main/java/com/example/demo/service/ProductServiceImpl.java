package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.document.ProductDocument;
import com.example.demo.document.ProductStatus;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse saveProduct(ProductRequest request) {

        ProductDocument product = productMapper.toDocument(request);

        product.setCreatedAt(LocalDateTime.now());
        product.setId(UUID.randomUUID());
        product.setStatus(ProductStatus.ACTIVE);

        return productMapper.toResponse(
                productRepository.save(product));

    }

    @Override
    public ProductResponse getProductById(String id) {
        ProductDocument product= productRepository.findById(id)
        		.orElseThrow(()->new RuntimeException("Product not found"));
        
        return productMapper.toResponse(product);
    }
}