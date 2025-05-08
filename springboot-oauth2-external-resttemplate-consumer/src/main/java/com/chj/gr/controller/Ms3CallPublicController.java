package com.chj.gr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/call/public")
public class Ms3CallPublicController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.gateway.url}")
	private String apiGatewayUrl;

	/**
	 * MS1
	 */
	@GetMapping("/ms1/api/public/hello")
	public String callMs1Endpoint() {

		ResponseEntity<String> response = restTemplate.getForObject(apiGatewayUrl + "/ms1/api/public/hello",
				ResponseEntity.class);
		return response.getBody();
	}

	/**
	 * MS2
	 */
	@GetMapping("/ms2/api/public/hello")
	public String callMs12Endpoint() {

		ResponseEntity<String> response = restTemplate.getForObject(apiGatewayUrl + "/ms2/api/public/hello",
				ResponseEntity.class);
		return response.getBody();
	}

}
