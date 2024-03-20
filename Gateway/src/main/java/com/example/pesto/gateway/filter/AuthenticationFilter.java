package com.example.pesto.gateway.filter;

import com.example.pesto.gateway.dao.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Autowired
    WebClient.Builder webClient;

    @Value("${authentication-service.url}")
    String authUrl;

    @Override
    public GatewayFilter apply(Config config) {

        return(((exchange, chain) -> {

            ServerHttpRequest request;
            // header contains token or not
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                throw new RuntimeException("Missing Authorization header");
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if(authHeader != null && authHeader.startsWith("Bearer ")){
                authHeader = authHeader.substring(7);
            }

            // Rest call to Auth Service
            return validateToken(authHeader)
                    .flatMap(userDTO -> {
                        if (userDTO.getId() != null) {
                            exchange
                                    .getRequest()
                                    .mutate()
                                    .header("userId", userDTO.getId())
                                    .header("username", userDTO.getUsername())
                                    .header("user-Name", userDTO.getName())
                                    .header("user-Email", userDTO.getEmail())
                                    .header("user-Role", userDTO.getRole().toString())
                                    .build();
                            return chain.filter(exchange);
                        } else {
                            ServerHttpResponse response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            return response.setComplete();
                        }
                    });



        }));


    }

    public static class Config{

    }

    private Mono<UserDTO> validateToken(String token) {
        // Send a request to the AuthenticationService to validate the token
        // This can be done using WebClient
        String authenticationServiceUrl = authUrl+"/api/auth/validate-token?token="+token;
        return webClient.build().get()
                .uri(authenticationServiceUrl)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }
}
