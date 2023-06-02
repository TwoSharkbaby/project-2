package com.twosharkbaby.www.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.twosharkbaby.www.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

	@Query(value = "SELECT * FROM Movie ORDER BY releaseDate DESC LIMIT 10", nativeQuery = true)
	List<Movie> findRecentMovies();
	
	@Query(value = "select * from Movie where genre = '액션' order by releaseDate desc limit 10", nativeQuery = true)
    List<Movie> findActionMovies();
	
	@Query(value = "select * from Movie where genre = '로멘스' order by releaseDate desc limit 10", nativeQuery = true)
    List<Movie> findRomanceMovies();
	
	Movie findById(Long id);
	
	Long deleteById(Long id);
	
	Page<Movie> findByTitleContaining(String keyword, Pageable pageable);
	
}
