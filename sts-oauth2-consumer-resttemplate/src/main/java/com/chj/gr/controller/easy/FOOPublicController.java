package com.chj.gr.controller.easy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/public/foo")
public class FOOPublicController {

	@Autowired
    private RestTemplate restTemplate;
    
    private CallerDestinationProperties callerDestinationProperties;
    
    public FOOPublicController(CallerDestinationProperties callerDestinationProperties) {
		this.callerDestinationProperties = callerDestinationProperties;
	}
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/get")
    public String get() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/get",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/foo-properties")
    public String fooProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/foo-properties",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/bar")
    public String bar() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
    	String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/bar",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/bar-properties")
    public String barProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/bar-properties",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/hello")
    public String hello() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/hello",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/helloworld")
    public String helloworld() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/helloworld",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/helloworld/hello-property")
    public String helloProperty() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/helloworld/hello-property",
                String.class
        );
        return response;
    }
    
    @GetMapping(value = "/ms7/z-springboot-foo-service/helloworld/display-properties")
    public String displayProperties() {
    	DestinationClient destinationClient = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.FOO_REGISTRATION.getKey());
        
        String response = restTemplate.getForObject(
        		destinationClient.getResourceUri() + "/ms7/z-springboot-foo-service/helloworld/display-properties",
                String.class
        );
        return response;
    }
}