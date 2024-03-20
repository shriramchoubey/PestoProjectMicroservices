package com.example.pesto.productManagement.dto.response;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ViewProductDetailsResponsDTO{
    private String id;
    private String name;
    private String description;
    private String image;
    private String createdBy;
    private String lastUpdatedBy;
    private Long creationDate;
    private Long modifiedDate;

    private String message;
}

