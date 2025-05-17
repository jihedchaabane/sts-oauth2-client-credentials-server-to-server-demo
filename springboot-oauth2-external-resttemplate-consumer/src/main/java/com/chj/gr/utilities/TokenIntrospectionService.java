package com.chj.gr.utilities;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.model.TokenIntrospectionResponse;
import com.chj.gr.properties.ServiceParamsProperties;

@Service
public class TokenIntrospectionService {
	
    private final RestTemplate restTemplate;
	private final ServiceParamsProperties serviceParamsProperties;
	
	public TokenIntrospectionService(
			@Qualifier("restTemplate") RestTemplate restTemplate, 
			ServiceParamsProperties serviceParamsProperties) {
		this.restTemplate = restTemplate;
		this.serviceParamsProperties = serviceParamsProperties;
	}

	/**
	 * curl -X POST http://localhost:8764/oauth2/introspect -u client1:secret1 -d "token=MY_ACTIVE_TOKEN"
	 * curl -X POST http://localhost:8764/oauth2/introspect -u client1:secret1 -d "token=MY_EXPIRED_TOKEN"
	 */
    public boolean isTokenActive(String accessToken, String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<TokenIntrospectionResponse> response = this.restTemplate.postForEntity(
            		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/introspect"), // URL de l'endpoint d'introspection
                request,
                TokenIntrospectionResponse.class
            );
            TokenIntrospectionResponse introspection = response.getBody();
            return introspection != null && introspection.isActive();
        } catch (HttpClientErrorException e) {
            // En cas d'erreur, considérer le token comme inactif
            return false;
        }
    }
}
