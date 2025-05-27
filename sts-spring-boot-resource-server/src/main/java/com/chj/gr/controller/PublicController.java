package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sts-spring-boot-resource-server/public")
public class PublicController {

	@GetMapping("/hello")
    public String getProducts() {
        return "Hello from PUBLIC [sts-spring-boot-resource-server]";
    }
}
