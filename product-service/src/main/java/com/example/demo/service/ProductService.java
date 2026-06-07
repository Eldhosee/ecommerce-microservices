package com.example.demo.service;

import java.util.UUID;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;


public interface ProductService {
	ProductResponse saveProduct(ProductRequest product);
	ProductResponse getProductById(String id) ;
}
