package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springboot-oauth2-client2/public")
public class Public2Controller {

    @GetMapping("/hello")
    public String hello() {
    	
        return "Hello from PUBLIC [sts-springboot-oauth2-client2]";
    }
}
