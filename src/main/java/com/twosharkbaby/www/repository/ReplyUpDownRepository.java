package com.twosharkbaby.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twosharkbaby.www.domain.ReplyUpDown;

public interface ReplyUpDownRepository extends JpaRepository<ReplyUpDown, Integer>{

	ReplyUpDown findByReplyIdAndUserId(Long replyId, Long userId); 
	
	void deleteById(Long id);
	
	void deleteByReplyId(Long id);
}
