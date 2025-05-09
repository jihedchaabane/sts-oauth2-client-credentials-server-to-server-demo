package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.exceptions.CustomException;
import com.chj.gr.utilities.AccessTokenUtil;

@RestController
@RequestMapping("/call/secure")
public class Ms3CallProtectedMs1Controller {

	@Autowired
    private RestTemplate restTemplate;
	
    @Value("${authorization.server.url}")
    private String issuerUri;
    
    @Value("${api.gateway.url}")
    private String apiGatewayUrl;
  
    @Value("${authorization.ms1.clientId}")
    private String ms1ClientId;
    @Value("${authorization.ms1.clientSecret}")
    private String ms1ClientSecret;
    @Value("${authorization.ms1.scopes}")
    private String ms1Scopes;
    
    
    @Value("${authorization.ms2.clientId}")
    private String ms2ClientId;
    @Value("${authorization.ms2.clientSecret}")
    private String ms2ClientSecret;
    @Value("${authorization.ms2.scopes}")
    private String ms2Scopes;

    /**
     * MS1
     */
    /** ==> DOIT FONCTIONNER */
    @GetMapping("/ms1/api/secure/token")
    public String callMs11Endpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms1ClientId, ms1ClientSecret, ms1Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }

    /** ==> DOIT FONCTIONNER */
    @GetMapping("/ms1/api/secure/call-client2")
    public String callMs12Endpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms1ClientId, ms1ClientSecret, ms1Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms1/api/secure/call-client2",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    /** ==> NE DOIT PAS FONCTIONNER */
    @GetMapping("/ms1/api/secure/forbidden")
    public String callMs1Forbidden() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms1ClientId, ms1ClientSecret, ms1Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;
        try {
	        response = restTemplate.exchange(
	                apiGatewayUrl + "/ms1/api/secure/forbidden",
	                HttpMethod.GET,
	                entity,
	                String.class
	        );
        } catch (HttpClientErrorException e) {
        	throw new CustomException("Failed to obtain access token: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        }
        return response.getBody();
    }

    /**
     * Call ms1 avec (ms2ClientId, ms2ClientSecret).
     * ==> NE DOIT PAS FONCTIONNER car 'read' <> 'read write'.
     */
    @GetMapping("/ms1/api/secure/withClient2")
    public String callMs13Endpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms2ClientId, ms2ClientSecret, ms2Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    
    /**
     * Call ms1 avec des faux scopes.
     * ==> NE DOIT PAS FONCTIONNER.
     */
    @GetMapping("/ms1/api/secure/token/wrong/scopes")
    public String callMs1WrongScopesEndpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms1ClientId, ms1ClientSecret, "scope1 scope2");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
}
