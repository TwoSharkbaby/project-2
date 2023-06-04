package com.twosharkbaby.www.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.Movie;
import com.twosharkbaby.www.domain.MovieReview;
import com.twosharkbaby.www.domain.MovieScore;
import com.twosharkbaby.www.domain.ReviewUpDown;
import com.twosharkbaby.www.repository.ReviewRepository;
import com.twosharkbaby.www.repository.ReviewUpDownRepository;
import com.twosharkbaby.www.repository.ScoreRepository;
import com.twosharkbaby.www.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private ScoreRepository scoreRepository;
	
	@Autowired
	private ReviewUpDownRepository reviewUpDownRepository;

	@Transactional
	public int save(MovieReview movieReview) {
		movieReview.setBad(0L);
		movieReview.setGood(0L);
		if (reviewRepository.findByMovieIdAndUserId(movieReview.getMovie().getId(),
				movieReview.getUser().getId()) == null) {
			MovieReview tmp = reviewRepository.save(movieReview);
			MovieScore MovieScore = new MovieScore().builder().point(movieReview.getScorePoint())
					.movie(new Movie().builder().id(movieReview.getMovie().getId()).build()).movieReview(tmp).build();
			if (scoreRepository.save(MovieScore) != null) {
				return 1;
			}
			return 0;
		} else {
			return 0;
		}
	}

	@Transactional(readOnly = true)
	public List<MovieReview> findByMovieId(Long id) {
		return reviewRepository.findByMovieId(id);
	}

	@Transactional(readOnly = true)
	public MovieReview findById(Long id) {
		return reviewRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieReview> findByMovieIdWithPage(Long id, Pageable pageable) {
		return reviewRepository.findByMovieId(id, pageable);
	}

	@Transactional
	public int update(MovieReview movieReview) {
		MovieReview review = reviewRepository.findById(movieReview.getId());
		if(review != null) {
			review.setTitle(movieReview.getTitle());
			review.setContent(movieReview.getContent());
			review.setScorePoint(movieReview.getScorePoint());
			reviewRepository.save(review);
			scoreRepository.updatePoint(movieReview.getId(),movieReview.getScorePoint());
			return 1;
		} else {
			return 0; 
		}
	}

	@Transactional
	public Long deleteById(Long id){
		scoreRepository.deleteScore(id); 
		reviewUpDownRepository.deleteByMovieReviewId(id);
		return reviewRepository.deleteById(id);
	}
	
	@Transactional  
	public MovieReview up(ReviewUpDown reviewUpDown) { // 좋아요 1 싫어요2
		ReviewUpDown tmp = reviewUpDownRepository.findByMovieReviewIdAndUserId(reviewUpDown.getMovieReview().getId(), reviewUpDown.getUser().getId());
		reviewUpDown.setUpDown(1L);
		if(tmp == null) {
			reviewRepository.upGood(reviewUpDown.getMovieReview().getId()); 
			reviewUpDownRepository.save(reviewUpDown);
		} else if (tmp.getUpDown() == 1L) {
			reviewRepository.downGood(reviewUpDown.getMovieReview().getId());
			reviewUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 2L) {
			reviewRepository.downBad(reviewUpDown.getMovieReview().getId());
			reviewRepository.upGood(reviewUpDown.getMovieReview().getId()); 
			reviewUpDownRepository.deleteById(tmp.getId());
			reviewUpDownRepository.save(reviewUpDown);
		}
		return reviewRepository.findById(reviewUpDown.getMovieReview().getId());
	}
	
	@Transactional  
	public MovieReview down(ReviewUpDown reviewUpDown) { // 좋아요 1 싫어요2
		ReviewUpDown tmp = reviewUpDownRepository.findByMovieReviewIdAndUserId(reviewUpDown.getMovieReview().getId(), reviewUpDown.getUser().getId());
		reviewUpDown.setUpDown(2L);
		if(tmp == null) {
			reviewRepository.upBad(reviewUpDown.getMovieReview().getId());
			reviewUpDownRepository.save(reviewUpDown);
		} else if (tmp.getUpDown() == 2L) {
			reviewRepository.downBad(reviewUpDown.getMovieReview().getId());
			reviewUpDownRepository.deleteById(tmp.getId());
		} else if (tmp.getUpDown() == 1L) {
			reviewRepository.downGood(reviewUpDown.getMovieReview().getId());
			reviewRepository.upBad(reviewUpDown.getMovieReview().getId());
			reviewUpDownRepository.deleteById(tmp.getId());
			reviewUpDownRepository.save(reviewUpDown);
		}
		return reviewRepository.findById(reviewUpDown.getMovieReview().getId());
	}
	
}
