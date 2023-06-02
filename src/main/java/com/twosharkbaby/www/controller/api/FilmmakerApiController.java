package com.twosharkbaby.www.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twosharkbaby.www.domain.Filmmaker;
import com.twosharkbaby.www.service.FilmmakerService;

@RestController
public class FilmmakerApiController {

	@Autowired
	private FilmmakerService filmmakerService;

	@GetMapping("/filmmaker/{filmmakerId}")
	@ResponseBody
	public ResponseEntity<Filmmaker> getDetail(@PathVariable Long filmmakerId) {
		return new ResponseEntity<>(filmmakerService.findById(filmmakerId), HttpStatus.OK);
	}

	@DeleteMapping("/admin/filmmaker/{filmmakerId}")
	@ResponseBody
	public ResponseEntity<Long> delete(@PathVariable Long filmmakerId) {
		return new ResponseEntity<>(filmmakerService.deleteById(filmmakerId), HttpStatus.OK);
	}

}
