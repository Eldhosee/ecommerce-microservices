package com.example.demo.service;

import java.util.Optional;

import com.example.demo.document.ProductDocument;

public interface ProductService {
	ProductDocument saveProduct(ProductDocument product);
	Optional<ProductDocument> getProductById(String id) ;
}
