package com.example.pesto.dao;


import com.example.pesto.enums.OrderStatus;
import com.example.pesto.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data @Table(name="orders")
@EqualsAndHashCode()
public class Order {

    @Id
    @Column(name = "id")
    private String id;

    private String prodId;

    private String userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String address;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private int quantity;
    private String modifiedBy;

    @Version
    private Integer version;

    private Long creationDate;
    private Long modifiedDate;

}
