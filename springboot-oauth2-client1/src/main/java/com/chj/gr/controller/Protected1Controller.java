package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/springboot-oauth2-client1/protected")
@PreAuthorize("hasAuthority('SCOPE_client1.read')")
public class Protected1Controller {

	private CallerDestinationProperties callerDestinationProperties;
	
	public Protected1Controller(CallerDestinationProperties callerDestinationProperties) {
		this.callerDestinationProperties = callerDestinationProperties;
	}

	@GetMapping("/token")
    public String protectedEndpoint(@Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        return "This is a protected API. Token received: " + token;
    }
	
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/call-client2")
    public String callClient2(JwtAuthenticationToken authentication) {
    	
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
    	
    	String accessToken = null;
    	// Récupérer le client autorisé pour client1
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
        		destinationClient.getRegistrationId(), authentication.getName()); // @TODO dindn't work..
        if (authorizedClient != null) {
        	// Obtenir le token d'accès
        	// dindn't work..
            accessToken = authorizedClient.getAccessToken().getTokenValue();
        }
        
        
        if (authentication.getCredentials() instanceof org.springframework.security.oauth2.jwt.Jwt) {
        	accessToken = ((org.springframework.security.oauth2.jwt.Jwt) authentication.getCredentials()).getTokenValue();
        } else {
        	return "Erreur: Aucun client OAuth2 autorisé trouvé";
        }

        // Configurer les headers avec le token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Appeler l'endpoint sécurisé de client2
        try {
            ResponseEntity<String> response = restTemplate.exchange(
            		destinationClient.getResourceUri().concat("/springboot-oauth2-client2/protected/hello"),
                    HttpMethod.GET,
                    entity,
                    String.class
            );
            return "Réponse de client2: " + response.getBody();
        } catch (Exception e) {
            return "Erreur lors de l'appel à client2: " + e.getMessage();
        }
    }
}