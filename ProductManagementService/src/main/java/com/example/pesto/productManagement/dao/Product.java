package com.example.pesto.productManagement.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.UUID;

@Entity
@Data
@Table(name="products")
@EqualsAndHashCode()
public class Product {
    @Id
    private String id;
    @NotBlank(message = "Product should have a name")
    private String name;
    private String description;
    private String image;
    private String createdBy;
    private String lastUpdatedBy;
    private Long creationDate;
    private Long modifiedDate;

    @Version
    private Integer version;

    public Product(){
    }
    public Product(String name, String description, String image, String createdBy){
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.image = image;
        this.creationDate= System.currentTimeMillis();
        this.modifiedDate= System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        this.id= uuid.toString();
    }

}
