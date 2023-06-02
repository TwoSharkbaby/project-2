package com.twosharkbaby.www.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twosharkbaby.www.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	@Query(value = "SELECT * FROM Board WHERE type = '일반'", nativeQuery = true)
	Page<Board> findAll(Pageable pageable);
	
	@Query(value = "SELECT * FROM Board WHERE type = '공지' limit 3", nativeQuery = true)
	List<Board> notice();

	Board findById(Long id);
	
	@Modifying
	@Query("update Board set counting = (counting)+1 where id = :id")
	void upCount(@Param("id") Long id);
	
	Long deleteById(Long id);
	
	@Modifying
	@Query("update Board set good = (good)+1 where id = :id")
	void upGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update Board set good = (good)-1 where id = :id")
	void downGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update Board set bad = (bad)+1 where id = :id")
	void upBad(@Param("id")Long id); 
	
	@Modifying
	@Query("update Board set bad = (bad)-1 where id = :id")
	void downBad(@Param("id")Long id); 
	
}
