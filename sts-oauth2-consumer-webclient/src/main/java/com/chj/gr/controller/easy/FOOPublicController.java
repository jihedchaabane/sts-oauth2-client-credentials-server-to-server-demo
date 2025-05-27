package com.chj.gr.controller.easy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/public/foo")
public class FOOPublicController {

    private WebClient webClient;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public FOOPublicController(WebClient webClient, CallerDestinationProperties callerDestinationProperties) {
		this.webClient = webClient;
		this.callerDestinationProperties = callerDestinationProperties;
	}
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/get")
    public String get() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/get"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/foo-properties")
    public String fooProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/foo-properties"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/bar")
    public String bar() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/bar"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/bar-properties")
    public String barProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/bar-properties"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/hello")
    public String hello() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/hello"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/helloworld")
    public String helloworld() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/helloworld"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/helloworld/hello-property")
    public String helloProperty() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/helloworld/hello-property"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
    
    @GetMapping(value = "/ms6/z-springboot-foo-service/helloworld/display-properties")
    public String displayProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        String responseJson = this.webClient
        		.get()
                .uri(destinationClient.getResourceUri().concat("/ms7/z-springboot-foo-service/helloworld/display-properties"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return responseJson;
    }
}