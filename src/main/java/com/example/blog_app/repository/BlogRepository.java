package com.example.blog_app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog_app.*;
import com.example.blog_app.entity.Blog;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>{
	Page<Blog> findByTitleContainingIgnoreCase(String title,Pageable pageable);
}
