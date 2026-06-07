package com.example.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.document.ProductDocument;

public interface ProductRepository
       extends ElasticsearchRepository<ProductDocument, String> {

}