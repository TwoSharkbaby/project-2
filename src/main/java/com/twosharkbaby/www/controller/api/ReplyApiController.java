package com.twosharkbaby.www.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twosharkbaby.www.domain.Reply;
import com.twosharkbaby.www.domain.ReplyUpDown;
import com.twosharkbaby.www.service.ReplyService;

@RestController
public class ReplyApiController {

	@Autowired
	private ReplyService replyService;
	
	@PreAuthorize("principal.user.id == #userId or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/auth/reply/{id}/{userId}")
	@ResponseBody
	public ResponseEntity<Long> delete(@PathVariable Long id, @PathVariable Long userId) {
		return new ResponseEntity<>(replyService.deleteById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/auth/reply", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reply> save(@RequestBody Reply reply) {
		return new ResponseEntity<>(replyService.save(reply), HttpStatus.OK);
	}
	
	@PreAuthorize("principal.user.id == #reply.user.id")
	@PutMapping(value = "/auth/reply", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> update(@RequestBody Reply reply) {
		return new ResponseEntity<>(replyService.update(reply), HttpStatus.OK);
	}
	
	@PostMapping(value = "/auth/reply/up", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reply> up(@RequestBody ReplyUpDown replyUpDown) {
		return new ResponseEntity<>(replyService.up(replyUpDown), HttpStatus.OK);
	}
	
	@PostMapping(value = "/auth/reply/down", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Reply> down(@RequestBody ReplyUpDown replyUpDown) {
		return new ResponseEntity<>(replyService.down(replyUpDown), HttpStatus.OK);
	}

}
