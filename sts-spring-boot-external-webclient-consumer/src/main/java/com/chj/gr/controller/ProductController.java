package com.chj.gr.controller;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.model.Product;

@RestController
public class ProductController {

    @Autowired
    private WebClient webClient;
    
    @Value("${params.clients.resourceUri:Config Server is not working. Please check...}")
    /**
     * - "sts-spring-boot-resource-server uri".
     * @TODO "springboot-conf-gateway-api-oauth2" uri.
     */
    private String resourceUri;// "sts-spring-boot-resource-server" uri.

    @GetMapping(value = "/products-view")
    public List<Product> getProducts() {
        return this.webClient
                .get()
                .uri(resourceUri + "/api/secure/products")
                .attributes(clientRegistrationId("products-client-client-credentials"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
    
    @GetMapping(value = "/public")
    public String getPublic() {
        
        String responseJson = this.webClient
        		.get()
                .uri(resourceUri + "/api/public/hello")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
}