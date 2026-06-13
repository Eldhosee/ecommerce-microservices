package com.example.demo.document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "products")
public class ProductDocument {

	@Id
	private String  id;
	
	private String name;
	private String description;
	private String category;
	private BigDecimal price; 
	private ProductStatus status;
	@Field(type = FieldType.Date,
	           format = DateFormat.date_hour_minute_second_fraction)
	private LocalDateTime deletedAt;
	@Field(type = FieldType.Date,
	           format = DateFormat.date_hour_minute_second_fraction)
	private LocalDateTime createdAt;
	@Field(type = FieldType.Date,
	           format = DateFormat.date_hour_minute_second_fraction)
	private LocalDateTime updatedAt;
}
