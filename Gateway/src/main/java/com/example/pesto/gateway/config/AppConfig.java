package com.example.pesto.gateway.config;

import com.example.pesto.gateway.filter.AuthenticationFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public AuthenticationFilter myCustomFilterFactory() {
        return new AuthenticationFilter();
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
