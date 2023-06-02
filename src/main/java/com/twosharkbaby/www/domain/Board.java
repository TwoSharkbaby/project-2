package com.twosharkbaby.www.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class Board {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private BoardType type;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Column(nullable = false)
	@Lob
	private String content;
	
	@Column(nullable = false, length = 16)
	private String nickname;
	
	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Column(nullable = false)
	private Long counting;
	
	@Column(nullable = false)
	private Long good;

	@Column(nullable = false)
	private Long bad;
	
	@ManyToOne(fetch = FetchType.EAGER) // many = board, one = user, EAGER = 모든정보 다 들고옴
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"}) 
	private User user; 
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
	
	
	
	
	
	
	
}
