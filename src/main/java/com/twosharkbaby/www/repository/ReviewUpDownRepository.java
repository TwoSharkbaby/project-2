package com.twosharkbaby.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twosharkbaby.www.domain.ReviewUpDown;

public interface ReviewUpDownRepository extends JpaRepository<ReviewUpDown, Integer>{

	ReviewUpDown findByMovieReviewIdAndUserId(Long reviewId, Long userId); 
	
	void deleteById(Long id);
	
	void deleteByMovieReviewId(Long id);
}
