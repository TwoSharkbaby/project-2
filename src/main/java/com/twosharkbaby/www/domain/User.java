package com.twosharkbaby.www.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(length = 16, unique = true)
	private String nickname;
	
	@Column(length = 50)
	private String email;
	
	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@Column(length = 50)
	private String provider;
	
	@Column(length = 100)
	private String providerId; 
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String nickname, String email, RoleType role, String provider, String providerId,
			Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}
}
