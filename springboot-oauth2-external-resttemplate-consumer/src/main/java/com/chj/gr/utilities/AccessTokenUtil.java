package com.chj.gr.utilities;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.model.TokenResponse;

public class AccessTokenUtil {

	private AccessTokenUtil() {
	}

	public static String getAccessToken(
			RestTemplate restTemplate, 
			String issuerUri, 
			String clientId, 
			String clientSecret, 
			String scopes) {
		
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("scope", scopes);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
		try {
			ResponseEntity<TokenResponse> tokenResponse = restTemplate.postForEntity(issuerUri, request,
					TokenResponse.class);

			if (tokenResponse == null || tokenResponse.getBody() == null) {
				throw new RuntimeException("Invalid token response: Access token is null");
			}
			return tokenResponse.getBody().getAccess_token();
		} catch (HttpClientErrorException e) {
			throw new RuntimeException(
					"Failed to obtain access token: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
		}
    }
}
