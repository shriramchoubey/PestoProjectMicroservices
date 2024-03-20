package com.example.pesto.controller;


import com.example.pesto.dto.request.CancelOrderRequestDTO;
import com.example.pesto.dto.request.CreateOrderRequestDTO;
import com.example.pesto.dto.request.UpdateOrderRequestDTO;
import com.example.pesto.dto.response.*;

import com.example.pesto.enums.Role;
import com.example.pesto.exceptions.PestoUnAuthorisedUserException;
import com.example.pesto.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping(Endpoints.OrderAPI.BASE_URL)
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(Endpoints.OrderAPI.CREATE)
    public CreateOrderResponseDTO createOrder(@RequestHeader("userId") String userId, @RequestHeader("Authorization") String token, @Valid @RequestBody CreateOrderRequestDTO requestDTO){
        log.info("API request to create order for userId : "+userId);
        return orderService.createOrder(token, userId, requestDTO);
    }

    @PostMapping(Endpoints.OrderAPI.UPDATE)
    public UpdateOrderResponseDTO updateOrder(@RequestHeader("userId") String userId, @RequestHeader("user-Role") String role, @Valid @RequestBody UpdateOrderRequestDTO requestDTO){
        log.info("API request to update product status with userId : "+userId);

        if(role == null || !role.equals(Role.ADMIN.toString())){
            throw new PestoUnAuthorisedUserException();
        }
        return orderService.updateOrder(userId, requestDTO);
    }

    @GetMapping(Endpoints.OrderAPI.VIEW)
    public GetOrdersResponseDTO viewOrders(@RequestHeader("userId") String userId){
        log.info("API request to view order for userId: "+userId);
        return orderService.getOrders(userId);
    }

    @GetMapping(Endpoints.OrderAPI.VIEW_DETAILS)
    public GetOrderDetailsResponseDTO viewOrderDetails(@RequestHeader("Authorization") String token, @Valid @RequestParam String orderId){
        log.info("API request to view product details");
        return orderService.getOrderDetails(token, orderId);
    }

    @PostMapping(Endpoints.OrderAPI.CANCEL)
    public CancelOrderResponseDTO cancelOrder(@RequestHeader("userId") String userId, @Valid @RequestBody CancelOrderRequestDTO requestDTO){
        log.info("API request to cancel order by userId: "+userId);
        return orderService.cancelOrder(userId, requestDTO);
    }
}
