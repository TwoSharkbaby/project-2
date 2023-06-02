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
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 300)
	private String content;

	@Column(nullable = false)
	private Long good;

	@Column(nullable = false)
	private Long bad;
	
	@Column(nullable = false, length = 16)
	private String nickname;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="boardId")
	@JsonIgnoreProperties({"boardId"})
	private Board board;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"})
	private User user;
	
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createDate;
	
	@Column(nullable = false)
	private Long grp;
	
	@Column(nullable = false)
	private Long seq;
	
	@Column(nullable = false)
	private Long dep;
}
