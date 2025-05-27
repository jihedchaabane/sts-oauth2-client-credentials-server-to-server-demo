package com.chj.gr.controller;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/registration/client1")
public class WebClientClient1Controller {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public WebClientClient1Controller(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}

    @GetMapping(value = "/ms10/springboot-oauth2-client1/protected/token")
    public String readProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/ms10/springboot-oauth2-client1/protected/token"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
    @GetMapping(value = "/ms10/springboot-oauth2-client1/protected/call-client2")
    public String writeProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/ms10/springboot-oauth2-client1/protected/call-client2"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}