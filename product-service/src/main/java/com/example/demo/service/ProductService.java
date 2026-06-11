package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.ProductUpdateRequest;


public interface ProductService {
	List<ProductResponse> saveProduct(List<ProductRequest> product);
	ProductResponse getProductById(String id) ;
	ProductResponse updateProduct(String id,ProductUpdateRequest product);
	String deleteProduct(String id);
}
