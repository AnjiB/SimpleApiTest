package com.simple.api.core.auth;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.authentication.FormAuthScheme;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

public class Authenticator {
	
	private String username;
	
	private String password;
	
	public Authenticator(String username, String password) {
		
		this.username = username;
		this.password = password;
	}

	public AuthenticationScheme basicAuth() {
		
		BasicAuthScheme basicAuth = new BasicAuthScheme();
		basicAuth.setUserName(username);
		basicAuth.setPassword(password);
		return basicAuth;
	}
	
	public AuthenticationScheme preemptiveBasicAuth() {
		
		PreemptiveBasicAuthScheme preemptiveBasicAuth = new PreemptiveBasicAuthScheme();
		preemptiveBasicAuth.setUserName(username);
		preemptiveBasicAuth.setPassword(password);
		return preemptiveBasicAuth;	
	}
	
	
	public AuthenticationScheme formAuth(FormAuthConfig formAuthConfig) {
		
		FormAuthScheme formAuth = new FormAuthScheme();
		formAuth.setUserName(username);
		formAuth.setPassword(password);
		formAuth.setConfig(formAuthConfig);
		return formAuth;	
	}
	
	
	public AuthenticationScheme oAuth2() {
		String authAccessToken = getOAuth2AccessToken();
		OAuth2Scheme oauthTwo =  new OAuth2Scheme();
		oauthTwo.setAccessToken(authAccessToken);
		return oauthTwo;

	}

	private String getOAuth2AccessToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
