package com.chj.gr.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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

@Service
public class TokenIntrospectionService {
	
	@Autowired
    private RestTemplate restTemplate;
	@Value("${authorization.server.url}")
    private String issuerUri;
	
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
            ResponseEntity<TokenIntrospectionResponse> response = restTemplate.postForEntity(
            	"http://localhost:8764/oauth2/introspect", // URL de l'endpoint d'introspection
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
