package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sts-oauth2-client1-resource/public")
public class Public1Controller {

    @GetMapping("/hello")
    public String hello() {
    	
        return "Hello from PUBLIC [STS-OAUTH2-CLIENT1-RESOURCE]";
    }
}
