package com.example.blog_app.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
	@NotBlank(message = "title required")
	private String title;
	@NotBlank(message = "content required")
	private String content;
	@NotBlank(message = "author name required")
	private String author;
	@Min(value = 0,message = "Price should be positive")
	private Long price;
}

