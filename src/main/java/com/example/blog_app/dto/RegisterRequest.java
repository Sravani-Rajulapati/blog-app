package com.example.blog_app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {
	private String username;
	private String password;
	private String email;
	private String role;
}
