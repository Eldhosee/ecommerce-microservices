package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.demo.document.ProductStatus;

import lombok.Data;

@Data
public class ProductResponse {

	private UUID  id;
	
	private String name;
	private String description;
	private String category;
	private BigDecimal price; 
	private ProductStatus status;
	private LocalDateTime deletedAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}