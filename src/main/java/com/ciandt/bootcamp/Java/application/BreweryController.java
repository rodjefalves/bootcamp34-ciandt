package com.ciandt.bootcamp.Java.application;

import com.ciandt.bootcamp.Java.business.BreweryBusiness;
import com.ciandt.bootcamp.Java.domain.EmailValidationDTO;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    @Autowired
    private BreweryBusiness breweryBusiness;

    @GetMapping
    @Operation(summary = "Retorna a busca por cidades")
    public List<BrewerieWrapper> buscaBrewery(@RequestParam("city") Optional<String> city) {
        return breweryBusiness.findCities(city);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Faz pesquisa por cervejaria por Id")
    public BrewerieWrapper buscaPorId(@PathVariable String id) {
        return breweryBusiness.findId(id);
    }

    @PostMapping("/rating")
    @Operation(summary = "Avalia a cervejaria")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionaValidarEmail(@RequestBody EmailValidationDTO emailValidationDTO) {
        breweryBusiness.addRating(emailValidationDTO.getEmail(),
                emailValidationDTO.getStars(),
                emailValidationDTO.getBrewerieId());
    }
}
