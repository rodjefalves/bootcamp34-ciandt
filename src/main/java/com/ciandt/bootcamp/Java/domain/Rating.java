package com.ciandt.bootcamp.Java.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation for user rating.
 * @Author Augusto Santos
 * @Version 1.0
 */
@Data
@Document
public class Rating {

    @Id
    private Long id;
    private String email;
    private Double stars;
    private String brewerieId;
}
