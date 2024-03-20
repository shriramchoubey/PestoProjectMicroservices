package com.example.pesto.productManagement.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class HealthAPI {

        public static final String BASE_URL = "/api/";

        public static final String HEALTH = "/health";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public  static  class ProductAPI {
        public  static  final String BASE_URL = "/api/product";

        public static final  String CREATE = "/create";
        public static final  String VIEW = "/view";
        public static final  String VIEW_DETAIL = "/detail";
        public static final  String UPDATE = "/update";
        public static final  String DELETE = "/delete";


    }
}