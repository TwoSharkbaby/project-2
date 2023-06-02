package com.twosharkbaby.www.service;

import java.util.List;

import com.twosharkbaby.www.domain.Filmmaker;

public interface FilmmakerService {

	public List<Filmmaker> findByMovieId(Long id);
	
	public Filmmaker findById(Long id);
	
	public Filmmaker save(Filmmaker filmmaker);
	
	public Long deleteById(Long id);
	
}
