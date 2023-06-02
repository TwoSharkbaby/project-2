package com.twosharkbaby.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twosharkbaby.www.domain.RoleType;
import com.twosharkbaby.www.domain.User;
import com.twosharkbaby.www.dto.joinUserDto;
import com.twosharkbaby.www.dto.updateUserDto;
import com.twosharkbaby.www.repository.UserRepository;
import com.twosharkbaby.www.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Transactional
	public Long join(joinUserDto dto) {
		String rawPassword = dto.getPassword();
		String encPassword = encodePwd().encode(rawPassword);
		User tmp = new User().builder().role(RoleType.ROLE_USER).password(encPassword)
				.username(dto.getUsername()).nickname(dto.getNickname())
				.email(dto.getEmail()).build();
		User entity = userRepository.save(tmp);
		return entity.getId();
	}
	
	@Transactional(readOnly = true)
	public boolean idCheck(String username) {
		if(userRepository.findByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional(readOnly = true)
	public boolean nicknameCheck(String nickname) {
		if(userRepository.findByNickname(nickname) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional(readOnly = true)
	public User findUser(Long id) {
		return userRepository.findById(id);
	}
	
	@Transactional
	public Integer update(updateUserDto dto) {
		User user = userRepository.findById(dto.getId());
		String rawPassword = dto.getPassword();
		String encPassword = encodePwd().encode(rawPassword);
		user.setPassword(encPassword);
		user.setNickname(dto.getNickname());
		user.setEmail(dto.getEmail());
		return userRepository.updateUser(user.getId(), user.getPassword(), user.getNickname(), user.getEmail());
	}
	
}
