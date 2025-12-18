package com.example.blog_app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String title;
	private String content;
	private String author;
	private Long price;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt; 
	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
}
