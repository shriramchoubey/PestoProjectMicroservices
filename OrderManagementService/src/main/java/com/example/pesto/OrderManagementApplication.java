package com.example.pesto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;


@SpringBootApplication(scanBasePackages = {"com.example.pesto"})
@EntityScan(basePackages = {"com.example.pesto"})
@EnableJpaRepositories(basePackages = {"com.example.pesto"})
@EnableFeignClients(basePackages = {"com.example.pesto"})
public class OrderManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderManagementApplication.class, args);
        for (Map.Entry<String,String> entry : System.getenv().entrySet())
            System.out.println("______ENVIRONMENT______ Key = "+ entry.getKey()+", Value = "+ entry.getValue());
    }


}
