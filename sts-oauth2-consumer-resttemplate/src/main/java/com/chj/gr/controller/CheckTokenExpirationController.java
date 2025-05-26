package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chj.gr.utilities.JwtTokenValidator;
import com.chj.gr.utilities.TokenIntrospectionService;

@RestController
@RequestMapping("/springboot-oauth2-external-resttemplate-consumer/token/active")
public class CheckTokenExpirationController {

	@Autowired
	private TokenIntrospectionService tokenIntrospectionService;
	
	@GetMapping("/introspect")
	public boolean withAuthServerIntrospectionUri(String accessToken, String clientId, String clientSecret) {
		
		return tokenIntrospectionService.isTokenActive(accessToken, clientId, clientSecret);
	}
	
	@GetMapping("/jwt/claims")
	public boolean withJwtTokenClaims(String accessToken) {
		/**
		 * ???
		 */
		return JwtTokenValidator.isActive(accessToken);
	}


}
