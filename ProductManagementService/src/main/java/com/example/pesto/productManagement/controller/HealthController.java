package com.example.pesto.productManagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(Endpoints.HealthAPI.BASE_URL)
public class HealthController {

    @GetMapping(value = Endpoints.HealthAPI.HEALTH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHealth() {
        log.info("{} Health API called.", Endpoints.HealthAPI.HEALTH );
        return ResponseEntity.ok("{\"message\" : \"pesto ProductManagementService version 1.0\"}");
    }

}
