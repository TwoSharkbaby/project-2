package com.twosharkbaby.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twosharkbaby.www.domain.Filmmaker;

public interface FilmmakerRepository extends JpaRepository<Filmmaker, Integer>{
	
	List<Filmmaker> findByMovieId(Long movieId);

	Filmmaker findById(Long id);
	
	Long deleteById(Long id);
	
	void deleteByMovieId(Long id);
	
}
