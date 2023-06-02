package com.twosharkbaby.www.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twosharkbaby.www.domain.Reply;
import com.twosharkbaby.www.domain.ReplyUpDown;

public interface ReplyService {

	public Long findByCount(Long id);
	
	public Page<Reply> findByBoardIdWithPage(Long id, Pageable pageable);
	
	public Reply save(Reply reply);
	
	public Integer update(Reply reply);
	
	public Long deleteById(Long id);
	
	public Reply up(ReplyUpDown replyUpDown);
	
	public Reply down(ReplyUpDown replyUpDown);
	
}
