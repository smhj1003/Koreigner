package com.koreigner.biz.member.auth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverAPI20 extends DefaultApi20 implements SnsUrls {
//	private static NaverAPI20 _instance;
	private NaverAPI20() {
		
	}
	
	private static class InstanceHolder {
		private static final NaverAPI20 INSTANCE = new NaverAPI20();
	}
	
	public static NaverAPI20 instance() {
//		if (_instance == null) {
//			_instance = new NaverAPI20();
//		} 
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return NAVER_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return NAVER_AUTH;
	}
	
	//google은 scipejava가 기본적으로 제공하기 때문에 안만들어도 된다.
}
