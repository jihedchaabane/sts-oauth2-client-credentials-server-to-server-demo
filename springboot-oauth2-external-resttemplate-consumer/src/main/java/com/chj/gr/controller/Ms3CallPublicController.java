package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.enums.EnumResourceServer;
import com.chj.gr.properties.CallerDestinationProperties;
import com.chj.gr.properties.CallerDestinationProperties.DestinationClient;

@RestController
@RequestMapping("/call/public")
public class Ms3CallPublicController {

	@Autowired
	private RestTemplate restTemplate;
	
	private CallerDestinationProperties callerDestinationProperties;
	
	public Ms3CallPublicController(CallerDestinationProperties callerDestinationProperties) {
		this.callerDestinationProperties = callerDestinationProperties;
	}
	/**
	 * MS1
	 */
	@GetMapping("/ms1/api/public/hello")
	public String callMs1Endpoint() {
		DestinationClient destinationClientMs1 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_READ_WRITE.getKey());
		
		ResponseEntity<String> response = restTemplate.getForObject(
				destinationClientMs1.getResourceUri() + "/ms1/api/public/hello",
				ResponseEntity.class);
		return response.getBody();
	}

	/**
	 * MS2
	 */
	@GetMapping("/ms2/api/public/hello")
	public String callMs12Endpoint() {
		DestinationClient destinationClientMs2 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_READ_WRITE.getKey());
		
		ResponseEntity<String> response = restTemplate.getForObject(
				destinationClientMs2.getResourceUri() + "/ms2/api/public/hello",
				ResponseEntity.class);
		return response.getBody();
	}

}
