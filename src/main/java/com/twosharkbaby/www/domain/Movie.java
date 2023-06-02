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
public class Movie { 

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 50)
	private String director;

	@Column(nullable = false, length = 10)
	private String genre;

	@Column(nullable = false, length = 300)
	private String synopsis;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private Date releaseDate;
	
	@Column(nullable = false, length = 10)
	private String runtime;
	
	@Column(nullable = false, length = 16)
	private String editor;

	@ManyToOne(fetch = FetchType.EAGER) // many = board, one = user, EAGER = 모든정보 다 들고옴
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"}) 
	private User user; 

	@Column(nullable = false)
	private String img;   

	@Column(nullable = false)
	private Double scorePoint;

	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
}
