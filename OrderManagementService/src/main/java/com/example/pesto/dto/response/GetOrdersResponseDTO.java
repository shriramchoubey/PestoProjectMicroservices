package com.example.pesto.dto.response;

import com.example.pesto.dao.Order;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode()
public class GetOrdersResponseDTO {

    List<Order> orders;
}
