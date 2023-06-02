package com.twosharkbaby.www.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Filmmaker { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column(nullable = false, length = 10)
	private String role;
	
	@Column(nullable = false, length = 16)
	private String editor;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	
	@Column(nullable = false, length = 5)
	private String sex;
	
	@Column(length = 300)
	private String info;
	
	@Column(nullable = false)
	private String img; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"})
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="movieId")
	@JsonIgnoreProperties({"movieId"})
	private Movie movie;
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
	
}
