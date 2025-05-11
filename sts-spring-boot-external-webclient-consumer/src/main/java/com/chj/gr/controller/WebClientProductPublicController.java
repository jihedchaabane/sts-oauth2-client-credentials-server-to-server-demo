package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.properties.CallerDestinationProperties;
import com.chj.gr.properties.CallerDestinationProperties.DestinationClient;

@RestController
@RequestMapping("/public")
public class WebClientProductPublicController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    /**
     * - "sts-spring-boot-resource-server uri".
     * @TODO "springboot-conf-gateway-api-oauth2" uri.
     */
    public WebClientProductPublicController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}
    
    @GetMapping(value = "/hello1")
    public String getPublic() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_SPRING_BOOT_RESOURCE_SERVER_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/api/public/hello"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
}