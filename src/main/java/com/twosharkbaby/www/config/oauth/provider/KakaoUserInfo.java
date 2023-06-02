package com.twosharkbaby.www.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

	private Map<String, Object> attributes;
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getProviderId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() { 
		return "kakao";
	}

	@Override
	public String getEmail() {  
	    return null;
	}

	@Override
	public String getName() {
	    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
	    return (String) properties.get("nickname");
	}

}
