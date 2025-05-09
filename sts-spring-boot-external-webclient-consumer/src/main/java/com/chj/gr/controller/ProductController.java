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
    
    /**
     * - "sts-spring-boot-resource-server uri".
     * @TODO "springboot-conf-gateway-api-oauth2" uri.
     */
    @Value("${params.clients.resourceUri1}")
    private String resourceUri;// "sts-spring-boot-resource-server" uri.

    /**
     * Ce client n'a que le scope 'products.read' dans sa configuration.
     * DOIT FONCTIONNER car "sts-spring-boot-resource-server/api/secure/products/read"
     * 							==> @PreAuthorize("hasAuthority('SCOPE_products.read')")
     */
    @GetMapping(value = "/products-read")
    public List<Product> readProducts() {
        return this.webClient
                .get()
                .uri(resourceUri + "/api/secure/products/read")
                .attributes(clientRegistrationId("products-client-client-credentials"))
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
        return this.webClient
                .get()
                .uri(resourceUri + "/api/secure/products/write")
                .attributes(clientRegistrationId("products-client-client-credentials"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
    }
    
    /**
     * DOIT FONCTIONNER car /public est permit toujours dans la configuration de sécurité 
     * de 'sts-spring-boot-resource-server'.
     */
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