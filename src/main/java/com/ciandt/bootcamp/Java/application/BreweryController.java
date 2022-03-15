package com.ciandt.bootcamp.Java.application;

import com.ciandt.bootcamp.Java.business.BreweryBusiness;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/breweries")
@Api(value = "API Brewery")
public class BreweryController {

    @Autowired
    private BreweryBusiness breweryBusiness;

    @GetMapping
    @ApiOperation("Retorna a busca por cidades")
    public List<BrewerieWrapper> buscaBrewery(@RequestParam Optional<String> cidade) {
        return breweryBusiness.findCities(cidade);
    }

    @GetMapping("/{id}")
    @ApiOperation("Faz pesquisa por cervejaria por Id")
    public BrewerieWrapper buscaPorId(@RequestParam("id") String id){
        return breweryBusiness.findId(id);
    }

    @PostMapping("/{rating}")
    @ApiOperation("Avalia a cervejaria")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionaValidarEmail(@RequestParam("email") String email, @RequestBody Double star) {
        breweryBusiness.addRating(email, star);
    }
}
