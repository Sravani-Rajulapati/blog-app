package com.example.blog_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog_app.dto.BlogRequest;
import com.example.blog_app.dto.BlogResponse;
import com.example.blog_app.entity.Blog;
import com.example.blog_app.exception.ResourceNotFoundException;
import com.example.blog_app.repository.BlogRepository;
import com.example.blog_app.service.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class BlogServiceImpl implements BlogService{
	@Autowired
	private BlogRepository blogRepository;
	@Override
	public List<BlogResponse> postMultipleBlogs(List<BlogRequest> request) {
		List<Blog> blogs=request.stream().map(r ->{
			 Blog blog=new Blog();
			 blog.setTitle(r.getTitle());
			 blog.setContent(r.getContent());
			 blog.setAuthor(r.getAuthor());
			 blog.setPrice(r.getPrice());
			 
			 return blog;
		}).toList() ;
		
		List<Blog> savedBlogs=blogRepository.saveAll(blogs);
		
		return savedBlogs.stream().map(b-> new BlogResponse(
				b.getId(),
				b.getTitle(),
				b.getContent(),
				b.getAuthor(),
				b.getPrice(),
				b.getCreatedAt()))
				.toList();
	}

	@Override
	public BlogResponse postBlog(BlogRequest blogRequest) {
		// TODO Auto-generated method stub
		log.info("Creating blog with title: {}",blogRequest.getTitle());
		Blog blog=new Blog();
		blog.setTitle(blogRequest.getTitle());
		blog.setContent(blogRequest.getContent());
		blog.setAuthor(blogRequest.getAuthor());
		blog.setPrice(blogRequest.getPrice());
		Blog savedBlog=blogRepository.save(blog);
		return convertToResponse(savedBlog);
	}

	@Override
	public Page<BlogResponse> getAllBlogs(int page,int size,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		log.info("Fetching all blogs");
		Sort sort=sortDir.equalsIgnoreCase("desc")
				? Sort.by(sortBy).descending()
						:Sort.by(sortBy).ascending();
		Pageable pageable=PageRequest.of(page, size,sort);
		
		return blogRepository.findAll(pageable)
				.map(this::convertToResponse);
				
	}

	@Override
	public BlogResponse getBlogById(Long id) {
		// TODO Auto-generated method stub
		log.info("Fethching blog with id ",id);
		Blog blog=blogRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Blog not found"));
		
		return convertToResponse(blog);
	}

	@Override
	public BlogResponse updateBlogById(Long id, BlogRequest request) {
		// TODO Auto-generated method stub
		log.info("Updating blog with id {}",id);
		Blog blog=blogRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Blog is not found"));
		blog.setTitle(request.getTitle());
		blog.setContent(request.getContent());
		blog.setAuthor(request.getAuthor());
		blog.setPrice(request.getPrice());
		Blog updatedBlog=blogRepository.save(blog);
		return convertToResponse(updatedBlog);
	
	}

	@Override
	public String deleteBlogById(Long id) {
		log.info("Deleting blog with id {}",id);
		Blog blog=blogRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Blog is not found"));		
		blogRepository.delete(blog);
		return "Blog deleted successfully";
		
	}
	private BlogResponse convertToResponse(Blog blog) {
		BlogResponse response=new BlogResponse();
		response.setId(blog.getId());
		response.setTitle(blog.getTitle());
		response.setContent(blog.getContent());
		response.setAuthor(blog.getAuthor());
		response.setPrice(blog.getPrice());
		response.setCreatedAt(blog.getCreatedAt());
		return response;
		
	}

	@Override
	public Page<BlogResponse> searchBlogs(String title, int page, int size, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort=sortDir.equalsIgnoreCase("desc")
				?Sort.by(sortBy).descending()
						:Sort.by(sortBy).ascending();
		Pageable pageable =PageRequest.of(page, size,sort);
		
		Page<Blog> blogPage=blogRepository.findByTitleContainingIgnoreCase(title, pageable);
		
		return blogPage.map(this::convertToResponse);
	}

}
