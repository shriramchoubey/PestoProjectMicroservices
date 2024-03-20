package com.example.pesto.productManagement.controller;

import com.example.pesto.productManagement.dto.request.CreateProductRequestDTO;
import com.example.pesto.productManagement.dto.request.DeleteProductRequestDTO;
import com.example.pesto.productManagement.dto.request.UpdateProductRequestDTO;
import com.example.pesto.productManagement.dto.response.*;
import com.example.pesto.productManagement.enums.Role;
import com.example.pesto.productManagement.exceptions.PestoUnAuthorisedUserException;
import com.example.pesto.productManagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(Endpoints.ProductAPI.BASE_URL)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(Endpoints.ProductAPI.CREATE)
    public CreateProductResponseDTO createProducts(@RequestHeader("userId") String userId, @RequestHeader("user-Role") String role, @Valid @RequestBody CreateProductRequestDTO requestDTO){
        log.info("API request to create product");

        if(role == null || !role.equals(Role.ADMIN.toString())){
            throw new PestoUnAuthorisedUserException();
        }
        return productService.createProduct(userId, requestDTO);
    }

    @PutMapping(Endpoints.ProductAPI.UPDATE)
    public UpdateProductResponseDTO updateProduct(@RequestHeader("userId") String userId, @RequestHeader("user-Role") String role, @Valid @RequestBody UpdateProductRequestDTO requestDTO){
        log.info("API request to create product");

        if(role == null || !role.equals(Role.ADMIN.toString())){
            throw new PestoUnAuthorisedUserException();
        }
        return productService.updateProduct(userId, requestDTO);
    }

    @GetMapping(Endpoints.ProductAPI.VIEW)
    public GetProductsResponseDTO viewProducts(){
        log.info("API request to get products list");
        return productService.getProducts();
    }

    @GetMapping(Endpoints.ProductAPI.VIEW_DETAIL)
    public ViewProductDetailsResponsDTO viewProductDetail(@Valid @RequestParam String prodId){
        log.info("API request to view product details");
        return productService.viewProductDetail(prodId);
    }

    @DeleteMapping(Endpoints.ProductAPI.DELETE)
    public DeleteProductResponseDTO deleteProducts(@RequestHeader("user-Role") String role, @Valid @RequestBody DeleteProductRequestDTO requestDTO){
        log.info("API request to delete product");
        if(role == null || !role.equals(Role.ADMIN.toString())){
            throw new PestoUnAuthorisedUserException();
        }
        return productService.deleteProduct(requestDTO);
    }
}
