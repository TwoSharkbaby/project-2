package com.twosharkbaby.www.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twosharkbaby.www.domain.MovieReview;

public interface ReviewRepository extends JpaRepository<MovieReview, Integer>{

	List<MovieReview> findByMovieId(Long id);
	
	MovieReview findByMovieIdAndUserId(Long movieId, Long userId); 
	
	MovieReview findById(Long id);
	
	Page<MovieReview> findByMovieId(Long id, Pageable pageable);
	
	Long deleteById(Long id);
	
	Long deleteByMovieId(Long id);
	
	@Modifying
	@Query("update MovieReview set good = (good)+1 where id = :id")
	void upGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update MovieReview set good = (good)-1 where id = :id")
	void downGood(@Param("id")Long id); 
	
	@Modifying
	@Query("update MovieReview set bad = (bad)+1 where id = :id")
	void upBad(@Param("id")Long id); 
	
	@Modifying
	@Query("update MovieReview set bad = (bad)-1 where id = :id")
	void downBad(@Param("id")Long id); 
	
}
