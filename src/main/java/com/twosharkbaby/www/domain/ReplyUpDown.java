package com.twosharkbaby.www.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class ReplyUpDown {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="replyId")
	@JsonIgnoreProperties({"replyId"})	
	private Reply reply; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties({"userId"})
	private User user;
	
	@Column(nullable = false)
	private Long upDown; 

}
