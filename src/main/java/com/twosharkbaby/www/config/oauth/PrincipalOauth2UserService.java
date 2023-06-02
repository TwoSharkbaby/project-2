package com.twosharkbaby.www.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.twosharkbaby.www.config.auth.PrincipalDetails;
import com.twosharkbaby.www.config.oauth.provider.FacebookUserInfo;
import com.twosharkbaby.www.config.oauth.provider.GoogleUserInfo;
import com.twosharkbaby.www.config.oauth.provider.KakaoUserInfo;
import com.twosharkbaby.www.config.oauth.provider.NaverUserInfo;
import com.twosharkbaby.www.config.oauth.provider.OAuth2UserInfo;
import com.twosharkbaby.www.domain.RoleType;
import com.twosharkbaby.www.domain.User;
import com.twosharkbaby.www.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserRepository userRepository;
	
	// 함수 종료시 @AuthenticationPrincipal 어노테이션 만들어짐
	// userRequest 는 code를 받아서 accessToken을 응답 받은 객체
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest); // google의 회원 프로필 조회
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			oAuth2UserInfo = new KakaoUserInfo((Map)oAuth2User.getAttributes());
		}else {
			System.out.println("지원하지 않은 방식입니다");
		}
		
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider+"_"+providerId;
		String password = encodePwd().encode("TwoSharkBaby");
		String email = oAuth2UserInfo.getEmail();
		
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			userEntity = User.builder().username(username).password(password).email(email).role(RoleType.ROLE_USER)
					.provider(provider).providerId(providerId).build();
			userRepository.save(userEntity);
		} 
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
	
}
