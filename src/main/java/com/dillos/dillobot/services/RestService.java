package com.dillos.dillobot.services;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    
  RestTemplate rest = new RestTemplate(
    new HttpComponentsClientHttpRequestFactory()
  );
    
  @Bean("rest")
  public RestTemplate getRest() {
		return this.rest;
	}
}
