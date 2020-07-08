package com.dillos.dillobot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    
    RestTemplate rest = new RestTemplate();
    
    @Bean("rest")
    public RestTemplate getRest() {
		return this.rest;
	}
    
}