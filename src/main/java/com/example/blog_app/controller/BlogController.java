package com.example.blog_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.dto.BlogRequest;
import com.example.blog_app.dto.BlogResponse;
import com.example.blog_app.service.BlogService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@PostMapping("/bulk")
	public ResponseEntity<List<BlogResponse>> postMultipleBlogs(@RequestBody List<BlogRequest> request){
		List<BlogResponse> responses=blogService.postMultipleBlogs(request);
		return ResponseEntity.ok(responses);
	}
	
	@PostMapping
	public ResponseEntity<BlogResponse> postBlog (@RequestBody BlogRequest request){
		return ResponseEntity.ok(blogService.postBlog(request));
	}
	@GetMapping
	public ResponseEntity<Page<BlogResponse>> getAllBlogs(@RequestParam (defaultValue = "0") int page,
															@RequestParam(defaultValue = "5") int size,
															@RequestParam(defaultValue = "createdAt") String sortBy,
															@RequestParam(defaultValue = "desc")String sortDir){
		return ResponseEntity.ok(blogService.getAllBlogs(page,size,sortBy,sortDir));
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id){
		return ResponseEntity.ok(blogService.getBlogById(id));
	}
	@GetMapping("/search")
	public ResponseEntity<Page<BlogResponse>> searchBlogs(
			@RequestParam(defaultValue = "")String title,
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "5")int size,
			@RequestParam(defaultValue = "createdAt")String sortBy,
			@RequestParam(defaultValue = "desc")String sortDir){
				return ResponseEntity.ok(blogService.searchBlogs(title, page, size, sortBy, sortDir));
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<BlogResponse> updateBlogById(@PathVariable Long id,@RequestBody BlogRequest request){
		return ResponseEntity.ok(blogService.updateBlogById(id, request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBlogById(@PathVariable Long id){
		 
		return ResponseEntity.ok(blogService.deleteBlogById(id));
	}

}
