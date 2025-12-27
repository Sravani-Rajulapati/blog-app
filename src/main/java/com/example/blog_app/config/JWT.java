package com.example.blog_app.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWT {
	private final String SECRECT="blog_secret_key";
	
	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()+86400000))
			.signWith(SignatureAlgorithm.HS256,SECRECT)
			.compact();
	}
	public String extractUsername(String token) {
		return Jwts.parser()
				.setSigningKey(SECRECT)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
