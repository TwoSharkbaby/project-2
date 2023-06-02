package com.twosharkbaby.www.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twosharkbaby.www.domain.MovieReview;
import com.twosharkbaby.www.domain.ReviewUpDown;
import com.twosharkbaby.www.service.ReviewService;

@RestController
public class ReviewApiController {

	@Autowired
	private ReviewService reviewService;

	@PreAuthorize("principal.user.id == #userId or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/auth/review/{id}/{userId}")
	@ResponseBody
	public ResponseEntity<Long> delete(@PathVariable Long id, @PathVariable Long userId) {
		return new ResponseEntity<>(reviewService.deleteById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/auth/review/up", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MovieReview> up(@RequestBody ReviewUpDown reviewUpDown) {
		return new ResponseEntity<>(reviewService.up(reviewUpDown), HttpStatus.OK);
	}
	
	@PostMapping(value = "/auth/review/down", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MovieReview> down(@RequestBody ReviewUpDown reviewUpDown) {
		return new ResponseEntity<>(reviewService.down(reviewUpDown), HttpStatus.OK);
	}

}