package com.example.pesto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;


@SpringBootApplication(scanBasePackages = {"com.example.pesto"})
@EnableEurekaServer
public class MainApplication {

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
        for (Map.Entry<String,String> entry : System.getenv().entrySet())
            System.out.println("______ENVIRONMENT______ Key = "+ entry.getKey()+", Value = "+ entry.getValue());
    }


}
