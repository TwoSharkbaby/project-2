package com.twosharkbaby.www.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.twosharkbaby.www.domain.User;
import com.twosharkbaby.www.repository.UserRepository;

// SecurityConfig에서 loginProcessingUrl("/loginProc") 설정한뒤 login요청이 오면 loadUserByUsername실행됨
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// 함수 종료시 @AuthenticationPrincipal 어노테이션 만들어짐
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

	
	
}
