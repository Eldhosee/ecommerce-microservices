package com.example.demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class ProductUpdateRequest {
	 
	    private String name;

	    private String description;

	    private String category;

	    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
	    private BigDecimal price;
}
