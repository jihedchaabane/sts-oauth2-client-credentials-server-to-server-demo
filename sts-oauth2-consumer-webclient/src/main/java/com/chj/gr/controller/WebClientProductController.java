package com.chj.gr.controller;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.model.Product;

@RestController
@RequestMapping("/registration/product")
public class WebClientProductController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public WebClientProductController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}

    @GetMapping(value = "/ms12/sts-spring-boot-resource-server/protected/read")
    public List<Product> readProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/ms12/sts-spring-boot-resource-server/protected/read"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
    
    @GetMapping(value = "/ms12/sts-spring-boot-resource-server/protected/write")
    public List<Product> writeProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/ms12/sts-spring-boot-resource-server/protected/write"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
}