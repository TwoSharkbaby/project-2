package com.twosharkbaby.www.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.twosharkbaby.www.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig { // secured 어노테이션 활성화 / preAuthorize 어노테이션 활성화

	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/auth/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/", true)
			.and()
			.oauth2Login().loginPage("/loginForm").defaultSuccessUrl("/", true).userInfoEndpoint().userService(principalOauth2UserService);
		return http.build();
	}

}
