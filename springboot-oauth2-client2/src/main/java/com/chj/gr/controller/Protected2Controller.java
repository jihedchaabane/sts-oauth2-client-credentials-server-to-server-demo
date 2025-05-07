package com.chj.gr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class Protected2Controller {

//    @GetMapping("/principal")
//    public String hello(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
//    	
//        return "Hello from client2! Client ID: " + principal.getAttribute("client_id");
//    }
	
	@GetMapping("/hello")
    public String hello() {
    	
        return "Hello from SECURED client2 ..";
    }
    
}
