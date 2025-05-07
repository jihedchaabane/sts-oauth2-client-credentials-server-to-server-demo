package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class Public1Controller {

    @GetMapping("/hello")
    public String hello() {
    	
        return "Hello from PUBLIC client1..";
    }
}
