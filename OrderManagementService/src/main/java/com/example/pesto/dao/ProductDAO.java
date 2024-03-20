package com.example.pesto.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode()
public class ProductDAO {
    private String id;
    private String name;
    private String description;
    private String image;
    private Long creationDate;
    private Long modifiedDate;
    private String message;
}
