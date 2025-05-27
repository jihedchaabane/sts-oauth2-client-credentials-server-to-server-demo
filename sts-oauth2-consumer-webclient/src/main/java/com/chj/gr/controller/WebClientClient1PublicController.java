package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/public/client1")
public class WebClientClient1PublicController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public WebClientClient1PublicController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}
    
    @GetMapping(value = "/hello")
    public String getPublic() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms10/sts-oauth2-client1-resource/public/hello"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
}