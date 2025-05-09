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
import org.springframework.web.client.RestTemplate;

import com.chj.gr.utilities.AccessTokenUtil;

@RestController
@RequestMapping("/call/secure")
public class Ms3CallProtectedMs2Controller {

	@Autowired
    private RestTemplate restTemplate;
	
    @Value("${authorization.server.url}")
    private String issuerUri;
    
    @Value("${params.gateway.uri}")
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
     * MS2
     */
    /** ==> DOIT FONCTIONNER !!!!!!!!!!!!!!!*/
    @GetMapping("/ms2/api/secure/hello")
    public String callMs21Endpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms2ClientId, ms2ClientSecret, ms2Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms2/api/secure/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    /**
     * Call ms2 avec (ms1ClientId, ms1ClientSecret).
     * ==> FONCTIONNE car 'read' est inclut dans 'read write'.
     */
    @GetMapping("/ms2/api/secure/withClient1")
    public String callMs22Endpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms1ClientId, ms1ClientSecret, ms1Scopes);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms2/api/secure/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
    
    /**
     * Call ms2 avec des faux scopes.
     * ==> NE DOIT PAS FONCTIONNER.
     */
    @GetMapping("/ms2/api/secure/hello/wrong/scopes")
    public String callMs2WrongScopesEndpoint() {
        String token = AccessTokenUtil.getAccessToken(restTemplate, issuerUri, ms2ClientId, ms2ClientSecret, "scope3");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiGatewayUrl + "/ms2/api/secure/hello",
                HttpMethod.GET,
                entity,
                String.class
        );
        return response.getBody();
    }
}
