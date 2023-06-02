package com.twosharkbaby.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twosharkbaby.www.domain.MovieScore;

public interface ScoreRepository extends JpaRepository<MovieScore, Integer>{

	@Query(value = "SELECT AVG(point) FROM MovieScore WHERE movieId = :movieId", nativeQuery = true)
	Double scorePoint(@Param("movieId") Long movieId);
	
	@Modifying
	@Query(value = "update MovieScore set point = :point where id = :id", nativeQuery = true)
	void updatePoint(@Param("id") Long id, @Param("point") Double point); 
	
	@Modifying
	@Query("DELETE FROM MovieScore ms WHERE ms.movieReview.id = :id")
	void deleteScore(@Param("id") Long id);
}
