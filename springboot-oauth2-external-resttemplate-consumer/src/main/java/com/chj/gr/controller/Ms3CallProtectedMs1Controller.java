package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.exceptions.CustomException;
import com.chj.gr.properties.CallerDestinationProperties;
import com.chj.gr.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.properties.ServiceParamsProperties;
import com.chj.gr.utilities.AccessTokenUtil;

@RestController
@RequestMapping("/call/secure")
public class Ms3CallProtectedMs1Controller {

    private final RestTemplate restTemplate;
	private final CallerDestinationProperties callerDestinationProperties;
	private final ServiceParamsProperties serviceParamsProperties;
	
    public Ms3CallProtectedMs1Controller(@Qualifier("restTemplate") RestTemplate restTemplate,
			CallerDestinationProperties callerDestinationProperties, ServiceParamsProperties serviceParamsProperties) {
		this.restTemplate = restTemplate;
		this.callerDestinationProperties = callerDestinationProperties;
		this.serviceParamsProperties = serviceParamsProperties;
	}

	/**
     * MS1
     */
    @GetMapping("/ms1/api/secure/token")
    public String callMs11Endpoint() {
    	
    	DestinationClient destinationClientMs1 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(this.restTemplate, 
        		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/token"), 
        		destinationClientMs1.getClientId(), 
        		destinationClientMs1.getClientSecret(), 
        		destinationClientMs1.getScopes());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(
        		destinationClientMs1.getResourceUri() + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }

    @GetMapping("/ms1/api/secure/call-client2")
    public String callMs12Endpoint() {
    	
    	DestinationClient destinationClientMs1 = this.callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(restTemplate,
        		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/token"), 
        		destinationClientMs1.getClientId(), 
        		destinationClientMs1.getClientSecret(), 
        		destinationClientMs1.getScopes());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(
        		destinationClientMs1.getResourceUri() + "/ms1/api/secure/call-client2",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    @GetMapping("/ms1/api/secure/forbidden")
    public String callMs1Forbidden() {
    	
    	DestinationClient destinationClientMs1 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(this.restTemplate, 
        		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/token"), 
        		destinationClientMs1.getClientId(), 
        		destinationClientMs1.getClientSecret(), 
        		destinationClientMs1.getScopes());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = null;
        try {
	        response = this.restTemplate.exchange(
	        		destinationClientMs1.getResourceUri() + "/ms1/api/secure/forbidden",
	                HttpMethod.GET,
	                entity,
	                String.class
	        );
        } catch (HttpClientErrorException e) {
        	throw new CustomException("Failed to obtain access token: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        }
        return response.getBody();
    }

    @GetMapping("/ms1/api/secure/withClient2")
    public String callMs13Endpoint() {
    	DestinationClient destinationClientMs2 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(this.restTemplate,
        		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/token"), 
        		destinationClientMs2.getClientId(), 
        		destinationClientMs2.getClientSecret(), 
        		destinationClientMs2.getScopes());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(
        		destinationClientMs2.getResourceUri() + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    
    @GetMapping("/ms1/api/secure/token/wrong/scopes")
    public String callMs1WrongScopesEndpoint() {
    	DestinationClient destinationClientMs1 = this.callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(this.restTemplate,
        		this.serviceParamsProperties.getOauth2().getIssuerUri().concat("/oauth2/token"), 
        		destinationClientMs1.getClientId(), 
        		destinationClientMs1.getClientSecret(), 
        		"scope1 scope 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = this.restTemplate.exchange(
        		destinationClientMs1.getResourceUri() + "/ms1/api/secure/token",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
}
