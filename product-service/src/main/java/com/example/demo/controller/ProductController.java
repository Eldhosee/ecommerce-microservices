package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductListRequest;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.ProductUpdateRequest;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
    private   ProductService productService;


	@PostMapping
	public List<ProductResponse> saveProduct(
	        @Valid @RequestBody ProductListRequest request) {

	    return productService.saveProduct(request.getProducts());
	}

    @GetMapping("/{id}")
    public ProductResponse getProduct( @PathVariable String id) {
        return productService.getProductById(id);
    }
    
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable String id,  @Valid @RequestBody ProductUpdateRequest product)
    {
    	return productService.updateProduct(id,product);
    }
   
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id)
    {
    	return productService.deleteProduct(id);
    }
}