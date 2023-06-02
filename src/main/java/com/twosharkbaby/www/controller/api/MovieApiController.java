package com.twosharkbaby.www.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twosharkbaby.www.service.MovieService;

@RestController
public class MovieApiController {
	
	@Autowired
	private MovieService movieService; 

	@DeleteMapping("/admin/movie/{movieId}") 
	@ResponseBody
	public ResponseEntity<Long> deleteMovie(@PathVariable Long movieId) {
		System.out.println(movieId);
		return new ResponseEntity<>(movieService.deleteById(movieId), HttpStatus.OK);
	}


}
