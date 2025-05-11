package com.chj.gr.controller;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.model.Product;
import com.chj.gr.properties.CallerDestinationProperties;
import com.chj.gr.properties.CallerDestinationProperties.DestinationClient;

@RestController
@RequestMapping("/registration/read-write")
public class ProductController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    /**
     * - "sts-spring-boot-resource-server uri".
     * @TODO "springboot-conf-gateway-api-oauth2" uri.
     */
    public ProductController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}

    @GetMapping(value = "/products-read")
    public List<Product> readProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/api/secure/products/read"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
    
    @GetMapping(value = "/products-write")
    public List<Product> writeProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_REGISTRATION.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/api/secure/products/write"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
}