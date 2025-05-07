package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.model.TokenResponse;

@RestController
@RequestMapping("/call/secure")
public class Ms3CallProtectedController {

	@Autowired
    private RestTemplate restTemplate;

//    @Value("${authorization.server.url}")
    private String authServerUrl = "http://localhost:8764/oauth2/token";
//  @Value("${api.gateway.url}")
  private String apiGatewayUrl = "http://localhost:7766";
  
  
  
//    @Value("${authorization.ms1.client-id}")
    private String ms1ClientId = "client1";
//    @Value("${authorization.ms1.client-secret}")
    private String ms1ClientSecret = "secret1";
    
    
    
//  @Value("${authorization.ms2.client-id}")
    private String ms2ClientId = "client2";

//  @Value("${authorization.ms2.client-secret}")
    private String ms2ClientSecret = "secret2";


    private String getAccessToken(String clientId, String clientSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(authServerUrl, request, TokenResponse.class);
        return response.getBody().getAccess_token();
    }

    /**
     * MS1
     */
    @GetMapping("/ms1/api/secure/token")
    public String callMs1Endpoint() {
        String token = getAccessToken(ms1ClientId, ms1ClientSecret);
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
    
    @GetMapping("/ms1/api/secure/call-client2")
    public String callMs2Endpoint() {
        String token = getAccessToken(ms1ClientId, ms1ClientSecret);
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
    

    /**
     * MS2
     */
    @GetMapping("/ms2/api/secure/cl1")
    public String callMs12Endpoint() {
        String token = getAccessToken(ms1ClientId, ms1ClientSecret);
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
    
    @GetMapping("/ms2/api/secure/cl2")
    public String callMs123Endpoint() {
        String token = getAccessToken(ms2ClientId, ms2ClientSecret);
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
}
