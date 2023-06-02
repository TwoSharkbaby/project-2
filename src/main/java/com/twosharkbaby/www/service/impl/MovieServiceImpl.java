package com.twosharkbaby.www.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.Movie;
import com.twosharkbaby.www.repository.FilmmakerRepository;
import com.twosharkbaby.www.repository.MovieRepository;
import com.twosharkbaby.www.repository.ReviewRepository;
import com.twosharkbaby.www.repository.ReviewUpDownRepository;
import com.twosharkbaby.www.repository.ScoreRepository;
import com.twosharkbaby.www.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private FilmmakerRepository filmmakerRepository;
	
	@Autowired 
	private ScoreRepository scoreRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReviewUpDownRepository reviewUpDownRepository;
	
	@Transactional(readOnly = true)
	public List<Movie> findRecentMovies(){
		return movieRepository.findRecentMovies();
	}
	
	@Transactional(readOnly = true)
	public List<Movie> findActionMovies(){
		return movieRepository.findActionMovies();
	}
	
	@Transactional(readOnly = true)
	public List<Movie> findRomanceMovies(){
		return movieRepository.findRomanceMovies();
	}
	
	@Transactional
	public Movie save(Movie movie) {
		movie.setCreateDate(new Timestamp(System.currentTimeMillis()));
		movie.setScorePoint(0.0);
		return movieRepository.save(movie);
	}
	
	@Transactional(readOnly = true)
	public Movie findById(Long id){
		Movie movie = movieRepository.findById(id);
		movie.setScorePoint(scoreRepository.scorePoint(id));
		return movie;
	}
	
	@Transactional
	public Long deleteById(Long id){
		reviewRepository.findByMovieId(id).forEach(movieReview -> {
			scoreRepository.deleteScore(movieReview.getId()); 
			reviewUpDownRepository.deleteByMovieReviewId(movieReview.getId());
		});
		reviewRepository.deleteByMovieId(id);
		filmmakerRepository.deleteByMovieId(id);
		return movieRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Movie> findByTitleContaining(String keyword, Pageable pageable){
		return movieRepository.findByTitleContaining(keyword, pageable);
	}
	
}
