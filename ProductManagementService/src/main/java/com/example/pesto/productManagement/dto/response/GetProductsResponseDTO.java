package com.example.pesto.productManagement.dto.response;

import com.example.pesto.productManagement.dao.Product;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@EqualsAndHashCode()
public class GetProductsResponseDTO {
    List<Product> products;
}
