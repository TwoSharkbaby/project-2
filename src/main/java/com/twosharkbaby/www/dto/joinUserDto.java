package com.twosharkbaby.www.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class joinUserDto {

	@NotNull(message = "아이디 키캆이 없습니다.")
	@NotBlank(message = "아이디를 입력하세요.")
	@Size(max = 12, message = "아이디 길이를 초과하였습니다.")
	private String username;
	
	@NotNull(message = "비밀번호 키캆이 없습니다.")
	@NotBlank(message = "비밀번호를 입력하세요.")
	@Size(max = 12, message = "비밀번호 길이를 초과하였습니다.")
	private String password;
	
	@NotNull(message = "닉네임 키캆이 없습니다.")
	@NotBlank(message = "닉네임 입력하세요.")
	@Size(max = 16, message = "닉네임 길이를 초과하였습니다.")
	private String nickname;
	
	@NotNull(message = "이메일 키캆이 없습니다.")
	@NotBlank(message = "이메일 입력하세요.")
	@Size(max = 50, message = "이메일 길이를 초과하였습니다.")
	private String email;
	
}
