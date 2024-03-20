package com.example.pesto.controller;

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
    public  static  class OrderAPI {
        public  static  final String BASE_URL = "/api/order";

        public static final  String CREATE = "/create";

        public static final  String VIEW = "/view";
        public static final  String VIEW_DETAILS = "/details";
        public static final  String CANCEL = "/cancel";
        public static final  String UPDATE = "/update";


    }
}