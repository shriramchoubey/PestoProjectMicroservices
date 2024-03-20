package com.example.pesto.dto.response;

import com.example.pesto.dao.ProductDAO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder
@EqualsAndHashCode()
public class GetOrderDetailsResponseDTO {
    private String id;
    private ProductDAO product;


    private String status;
    private String address;


    private String paymentStatus;

    private Integer quantity;
    private Long creationDate;
    private Long modifiedDate;
}
