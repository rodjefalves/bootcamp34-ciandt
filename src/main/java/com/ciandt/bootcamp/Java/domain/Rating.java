package com.ciandt.bootcamp.Java.domain;

import lombok.Data;

/**
 * Representation for user rating.
 * @Author Augusto Santos
 * @Version 1.0
 */
@Data
public class Rating {

    private String email;
    private Double stars;
}
