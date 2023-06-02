package com.twosharkbaby.www.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

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
public class MovieReview { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 300)
	private String content;

	@Column(nullable = false)
	private Long good = 0L;

	@Column(nullable = false)
	private Long bad = 0L;
	
	@Column(nullable = false, length = 16)
	private String nickname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="movieId")
	@JsonIgnoreProperties({"movieId"})
	private Movie movie;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"})
	private User user;

	@Column(nullable = false)
	private Double scorePoint;
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;

}
