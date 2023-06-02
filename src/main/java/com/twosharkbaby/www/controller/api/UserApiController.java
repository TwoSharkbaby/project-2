package com.twosharkbaby.www.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twosharkbaby.www.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService; 

	@GetMapping("/idCheck/{username}")
	@ResponseBody
	public ResponseEntity<Boolean> idCheck(@PathVariable String username) {
		return new ResponseEntity<>(userService.idCheck(username), HttpStatus.OK);
	}

	@GetMapping("/nicknameCheck/{nickname}")
	@ResponseBody
	public ResponseEntity<Boolean> nicknameCheck(@PathVariable String nickname) {
		return new ResponseEntity<>(userService.nicknameCheck(nickname), HttpStatus.OK);
	}

}
