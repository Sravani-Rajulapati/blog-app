package com.example.blog_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog_app.entity.User;
import com.example.blog_app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		return userRepository.save(user);
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User user=userRepository.findByUsername(username)
				.orElseThrow(()->new RuntimeException("User not found"));
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid Credentials");
		}
		
		return user;
	}

}
