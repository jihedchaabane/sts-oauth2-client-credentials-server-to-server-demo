package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.utilities.AccessTokenUtil;

@RestController
@RequestMapping("/springboot-oauth2-external-resttemplate-consumer")
public class Ms3CallProtectedMs2Controller {

	@Autowired
    private RestTemplate restTemplate;
	
//    @Value("${params.oauth2.issuerUri}/oauth2/token")
    private String issuerUri = IssuerUri.ISSUER_URI.concat("/oauth2/token");
    
    private CallerDestinationProperties callerDestinationProperties;
	
	public Ms3CallProtectedMs2Controller(CallerDestinationProperties callerDestinationProperties) {
		this.callerDestinationProperties = callerDestinationProperties;
	}
	
    /**
     * MS2
     */
    @GetMapping("/ms11/springboot-oauth2-client2/protected/hello")
    public String callMs21Endpoint() {
    	DestinationClient destinationClientMs2 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, 
        		destinationClientMs2.getClientId(), 
        		destinationClientMs2.getClientSecret(), 
        		destinationClientMs2.getScopes());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
        		destinationClientMs2.getResourceUri() + "/ms11/springboot-oauth2-client2/protected/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    @GetMapping("/ms11/springboot-oauth2-client2/protected/withClient1")
    public String callMs22Endpoint() {
    	DestinationClient destinationClientMs1 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, 
        		destinationClientMs1.getClientId(), 
        		destinationClientMs1.getClientSecret(), 
        		destinationClientMs1.getScopes());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
        		destinationClientMs1.getResourceUri() + "/ms11/springboot-oauth2-client2/protected/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    @GetMapping("/ms11/springboot-oauth2-client2/protected/hello/wrong/scopes")
    public String callMs2WrongScopesEndpoint() {
    	DestinationClient destinationClientMs2 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
    	
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, 
        		destinationClientMs2.getClientId(), 
        		destinationClientMs2.getClientSecret(), 
        		"scope3");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
        		destinationClientMs2.getResourceUri() + "/ms11/springboot-oauth2-client2/protected/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
}
