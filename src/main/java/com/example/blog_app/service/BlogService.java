package com.example.blog_app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.blog_app.dto.BlogRequest;
import com.example.blog_app.dto.BlogResponse;
@Service
public interface BlogService {
	List<BlogResponse> postMultipleBlogs(List<BlogRequest> request);
	BlogResponse postBlog(BlogRequest blogRequest);
	Page<BlogResponse> getAllBlogs(int page,int size,String sortBy,String sortDir);
	BlogResponse getBlogById(Long id);
	BlogResponse updateBlogById(Long id,BlogRequest request);
	String deleteBlogById(Long id);
	Page<BlogResponse> searchBlogs(String title,
									int page,
									int size,
									String sortBy,
									String sortDir);
}
