package com.example.blog_app.controller;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.dto.LoginRequest;
import com.example.blog_app.entity.User;
import com.example.blog_app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user){
		return ResponseEntity.ok(userService.registerUser(user));
	}
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest request){
	return ResponseEntity.ok(userService.login(request.getUsername(),request.getPassword()));
	}

}
