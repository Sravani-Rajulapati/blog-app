package com.example.blog_app.service;

import org.springframework.stereotype.Service;

import com.example.blog_app.entity.User;

@Service
public interface UserService {
	User registerUser(User user);
	User login(String username,String password);
}