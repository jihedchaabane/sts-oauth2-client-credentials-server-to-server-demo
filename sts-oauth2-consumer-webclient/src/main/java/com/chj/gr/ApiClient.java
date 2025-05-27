package com.chj.gr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiClient {
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApiClient.class, args);
	}
}
