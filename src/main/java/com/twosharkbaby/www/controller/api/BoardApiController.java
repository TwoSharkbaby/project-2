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

import com.twosharkbaby.www.domain.Board;
import com.twosharkbaby.www.domain.BoardUpDown;
import com.twosharkbaby.www.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PreAuthorize("principal.user.id == #userId or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/auth/board/{id}/{userId}")
	@ResponseBody
	public ResponseEntity<Long> delete(@PathVariable Long id, @PathVariable Long userId) {
		return new ResponseEntity<>(boardService.deleteById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/auth/board/up", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Board> up(@RequestBody BoardUpDown boardUpDown) {
		return new ResponseEntity<>(boardService.up(boardUpDown), HttpStatus.OK);
	}
	
	@PostMapping(value = "/auth/board/down", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Board> down(@RequestBody BoardUpDown boardUpDown) {
		System.out.println(boardUpDown);
		return new ResponseEntity<>(boardService.down(boardUpDown), HttpStatus.OK);
	}

}