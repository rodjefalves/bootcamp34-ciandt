package com.ciandt.bootcamp.Java.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Representation for Breweries data stored on Open Brewery DB.
 * @Author Augusto Santos
 * @Version 1.0
 */
@Data
public class Brewerie {

    private String id;
    private String name;
    private String brewery_type;
    private String street;
    private String address_2;
    private String address_3;
    private String city;
    private String state;
    private String county_province;
    private String postal_code;
    private String country;
    private String longitude;
    private String latitude;
    private String phone;
    private String website_url;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
}
