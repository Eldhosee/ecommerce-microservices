package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

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
    public List<ProductResponse> saveProduct(List<ProductRequest> request) {

        List<ProductDocument> products = request.stream().map(i->
        {
        	ProductDocument newProduct=productMapper.toDocument(i);
        	newProduct.setCreatedAt(LocalDateTime.now());
        	newProduct.setId(UUID.randomUUID());
        	newProduct.setStatus(ProductStatus.ACTIVE);
        	
        	return newProduct;
        }
        ).toList();
        
        List<ProductDocument> savedProducts = StreamSupport
                .stream(productRepository.saveAll(products).spliterator(), false)
                .toList();

        return savedProducts.stream()
                .map(productMapper::toResponse)
                .toList();

    }

    @Override
    public ProductResponse getProductById(String id) {
        ProductDocument product= productRepository.findById(id)
        		.orElseThrow(()->new RuntimeException("Product not found"));
        
        return productMapper.toResponse(product);
    }
}