package com.ciandt.bootcamp.Java.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailValidationDTO {

    private String email;
    private Double stars;
    private String brewerieId;

}
