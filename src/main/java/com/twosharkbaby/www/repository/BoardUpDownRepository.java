package com.twosharkbaby.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twosharkbaby.www.domain.BoardUpDown;

public interface BoardUpDownRepository extends JpaRepository<BoardUpDown, Integer>{

	BoardUpDown findByBoardIdAndUserId(Long boardId, Long userId); 
	
	void deleteById(Long id);
	
	void deleteByBoardId(Long id);
}
