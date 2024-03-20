package com.example.pesto.clients;

import com.example.pesto.dao.ProductDAO;
import feign.HeaderMap;
import feign.Headers;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "Product-Management-Service", url="${product-management-service.url}")
public interface ProductClient {
    @GetMapping("/api/product/detail")
    ProductDAO getProduct(@RequestParam String prodId);
}
