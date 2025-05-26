package com.chj.gr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class TokenIntrospectionResponse {
	/**
	# introspect
	curl -X POST http://localhost:8764/oauth2/introspect -u client1:secret1 -d "token=MY_ACTIVE_TOKEN"
	{
		"active":true,
		"client_id":"client1",
		"iat":1746723999,
		"exp":1746724299,
		"scope":"read write",
		"token_type":"Bearer",
		"nbf":1746723999,
		"sub":"client1",
		"aud":[
			"client1"
		],
		"iss":"http://localhost:8764"
	}
	*/
    private boolean active; // Indique si le token est valide
    private long exp; // Date d'expiration (optionnel)

    // Getters et setters
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}
