package com.twosharkbaby.www.service;

import com.twosharkbaby.www.domain.User;
import com.twosharkbaby.www.dto.joinUserDto;
import com.twosharkbaby.www.dto.updateUserDto;

public interface UserService {

	public Long join(joinUserDto dto);
	
	public boolean idCheck(String username);

	public boolean nicknameCheck(String nickname);
	
	public User findUser(Long id);
	
	public Integer update(updateUserDto dto);
}
