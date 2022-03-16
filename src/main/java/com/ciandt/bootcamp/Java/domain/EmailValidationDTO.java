package com.ciandt.bootcamp.Java.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailValidationDTO {

    private String email;
    private Double stars;
    private String brewerieId;

}
