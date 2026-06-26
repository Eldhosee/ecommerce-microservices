package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
	
	@NotBlank(message="username is required")
	private String name;
	
	@NotBlank(message="password is required")
	private String password;
	
	
}
