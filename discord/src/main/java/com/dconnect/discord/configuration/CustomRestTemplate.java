package com.dconnect.discord.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomRestTemplate extends RestTemplate {

    public CustomRestTemplate() {
        super();
    }

    @Value("${discord.bot.token}")
    private String token;

    public HttpEntity<String> getCustomHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bot " + token);
        HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
        return entity;
    }
}
