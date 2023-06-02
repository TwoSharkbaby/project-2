package com.twosharkbaby.www.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twosharkbaby.www.domain.Movie;

public interface MovieService {

	public List<Movie> findRecentMovies();
	
	public List<Movie> findActionMovies();
	
	public List<Movie> findRomanceMovies();
	
	public Movie save(Movie movie);
	
	public Movie findById(Long id);
	
	public Long deleteById(Long id);
	
	public Page<Movie> findByTitleContaining(String keyword, Pageable pageable);
	
}
