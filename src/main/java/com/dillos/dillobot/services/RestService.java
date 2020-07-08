package com.dillos.dillobot.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    RestTemplateBuilder builder = new RestTemplateBuilder();
    
    @Bean("rest")
    public RestTemplate getRest() {
        return this.builder.build();
    }

}