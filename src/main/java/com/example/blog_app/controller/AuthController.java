package com.example.blog_app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog_app.config.JWT;
import com.example.blog_app.dto.LoginRequest;
import com.example.blog_app.dto.RegisterRequest;
import com.example.blog_app.entity.User;
import com.example.blog_app.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:5177")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWT jwt;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
		User user=new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setRole(registerRequest.getRole().toUpperCase());
		
		userRepository.save(user);
		
		return ResponseEntity.ok("User Registered Successfully!");
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest authRequest){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword()));
		String token=jwt.generateToken(authRequest.getUsername());
		
		return ResponseEntity.ok(Map.of("token",token));
	}
}
