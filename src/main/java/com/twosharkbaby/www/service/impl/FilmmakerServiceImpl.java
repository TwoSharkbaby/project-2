package com.twosharkbaby.www.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.Filmmaker;
import com.twosharkbaby.www.repository.FilmmakerRepository;
import com.twosharkbaby.www.service.FilmmakerService;

@Service
public class FilmmakerServiceImpl implements FilmmakerService{

	@Autowired
	private FilmmakerRepository filmmakerRepository ;
	
	@Transactional(readOnly = true)
	public List<Filmmaker> findByMovieId(Long id) {
		return filmmakerRepository.findByMovieId(id);
	}
	
	@Transactional(readOnly = true)
	public Filmmaker findById(Long id) {
		return filmmakerRepository.findById(id);
	}
	
	@Transactional
	public Filmmaker save(Filmmaker filmmaker) {
		filmmaker.setCreateDate(new Timestamp(System.currentTimeMillis()));
		return filmmakerRepository.save(filmmaker);
	}
	
	@Transactional
	public Long deleteById(Long id) {
		return filmmakerRepository.deleteById(id);
	}
	
}
