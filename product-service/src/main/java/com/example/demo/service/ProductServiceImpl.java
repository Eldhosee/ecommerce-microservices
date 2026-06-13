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
import com.example.demo.dto.ProductUpdateRequest;
import com.example.demo.exception.ProductNotFoundException;
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
        	newProduct.setId(UUID.randomUUID().toString());
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
        		.orElseThrow(()->new ProductNotFoundException(id));
        
        return productMapper.toResponse(product);
    }
    
    @Override
    public ProductResponse updateProduct(String id,ProductUpdateRequest product)
    {
    	ProductDocument existingProduct=productRepository.findById(id)
    			.orElseThrow(()-> new ProductNotFoundException(id));
    	
        productMapper.updateProduct(
                product,
                existingProduct);

        existingProduct.setUpdatedAt(LocalDateTime.now());

        ProductDocument updated =
                productRepository.save(existingProduct);

        return productMapper.toResponse(updated);
    }
    
    @Override
    public String deleteProduct(String id)
    {
    	ProductDocument product =productRepository.findById(id)
    			.orElseThrow(()-> new ProductNotFoundException(id));
    	
    	if(product.getStatus() == ProductStatus.DELETED) {
    	    throw new RuntimeException("Product already deleted");
    	}
    	product.setStatus(ProductStatus.DELETED);
    	product.setDeletedAt(LocalDateTime.now());
    	productRepository.save(product) ;
    	 return "Successfully deleted";
    	
    }
    
}