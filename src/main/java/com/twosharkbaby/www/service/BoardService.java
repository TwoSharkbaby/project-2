package com.twosharkbaby.www.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twosharkbaby.www.domain.Board;
import com.twosharkbaby.www.domain.BoardUpDown;

public interface BoardService {

	public List<Board> notice();

	public Page<Board> findAll(Pageable pageable);

	public Board save(Board board);
	
	public Board findByIdWithCounting(Long id);
	
	public Board findById(Long id);
	
	public Board update(Board board);
	
	public Long deleteById(Long id);
	 
	public Board up(BoardUpDown boardUpDown);
	
	public Board down(BoardUpDown boardUpDown);
	
}
