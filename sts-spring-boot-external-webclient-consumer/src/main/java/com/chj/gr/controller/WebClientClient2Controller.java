package com.chj.gr.controller;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.properties.CallerDestinationProperties;
import com.chj.gr.properties.CallerDestinationProperties.DestinationClient;

@RestController
@RequestMapping("/registration/client2")
public class WebClientClient2Controller {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    /**
     * - "springboot-oauth2-client1 uri".
     * @TODO "springboot-oauth2-client1" uri.
     */
    public WebClientClient2Controller(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}

    @GetMapping(value = "/api/secure/hello")
    public String readProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/api/secure/hello"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}