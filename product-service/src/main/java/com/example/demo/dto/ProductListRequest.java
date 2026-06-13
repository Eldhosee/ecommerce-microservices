package com.example.demo.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductListRequest {

    @NotEmpty(message = "Product list cannot be empty")
    private List<@Valid ProductRequest> products;
}