package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/public/client2")
public class WebClientClient2PublicController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public WebClientClient2PublicController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}
    
    @GetMapping(value = "/ms11/springboot-oauth2-client2/public/hello")
    public String getPublic() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms11/springboot-oauth2-client2/public/hello"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
}