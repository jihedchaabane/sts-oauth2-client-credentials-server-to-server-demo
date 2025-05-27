package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chj.gr.config.properties.CallerDestinationProperties;
import com.chj.gr.config.properties.CallerDestinationProperties.DestinationClient;
import com.chj.gr.enums.EnumResourceServer;

@RestController
@RequestMapping("/sts-oauth2-consumer-resttemplate")
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
	@GetMapping("/ms10/sts-oauth2-client1-resource/public/hello")
	public String callMs1Endpoint() {
		DestinationClient destinationClientMs1 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT1_RESOURCE_SERVER_REGISTRATION.getKey());
		
		String response = restTemplate.getForObject(
				destinationClientMs1.getResourceUri() + "/ms10/sts-oauth2-client1-resource/public/hello",
				String.class);
		return response;
	}

	/**
	 * MS2
	 */
	@GetMapping("/ms11/sts-oauth2-client2-resource/public/hello")
	public String callMs12Endpoint() {
		DestinationClient destinationClientMs2 = callerDestinationProperties.getDestinationClient(
    			EnumResourceServer.STS_OAUTH2_CLIENT2_RESOURCE_SERVER_REGISTRATION.getKey());
		
		String response = restTemplate.getForObject(
				destinationClientMs2.getResourceUri() + "/ms11/sts-oauth2-client2-resource/public/hello",
				String.class);
		return response;
	}

}
