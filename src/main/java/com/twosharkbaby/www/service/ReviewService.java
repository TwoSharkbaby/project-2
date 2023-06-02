package com.twosharkbaby.www.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twosharkbaby.www.domain.MovieReview;
import com.twosharkbaby.www.domain.ReviewUpDown;

public interface ReviewService {

	public int save(MovieReview movieReview);

	public List<MovieReview> findByMovieId(Long id) ;

	public MovieReview findById(Long id);
	
	public Page<MovieReview> findByMovieIdWithPage(Long id, Pageable pageable);

	public int update(MovieReview movieReview);

	public Long deleteById(Long id);
	
	public MovieReview up(ReviewUpDown reviewUpDown);
	
	public MovieReview down(ReviewUpDown reviewUpDown);
	
}
