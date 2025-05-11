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
@RequestMapping("/registration/read")
public class ProductReadController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    /**
     * - "sts-spring-boot-resource-server uri".
     * @TODO "springboot-conf-gateway-api-oauth2" uri.
     */
    public ProductReadController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}

	/**
     * Ce client n'a que le scope 'products.read' dans sa configuration.
     * DOIT FONCTIONNER car "sts-spring-boot-resource-server/api/secure/products/read"
     * 							==> @PreAuthorize("hasAuthority('SCOPE_products.read')")
     */
    @GetMapping(value = "/products-read")
    public List<Product> readProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_READ.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/api/secure/products/read"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
    
    /**
     * Ce client n'a que le scope 'products.read' dans sa configuration.
     * NE DOIT PAS FONCTIONNER car "sts-spring-boot-resource-server/api/secure/products/write"
     * 							==> @PreAuthorize("hasAuthority('SCOPE_products.write')")
     */
    @GetMapping(value = "/products-write")
    public List<Product> writeProducts() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_READ.getKey());
        return this.webClient
                .get()
                .uri(destinationClient.getResourceUri().concat("/api/secure/products/write"))
                .attributes(clientRegistrationId(destinationClient.getRegistrationId()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
}