package com.ciandt.bootcamp.Java.application;

import com.ciandt.bootcamp.Java.business.BreweryBusiness;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Brewery")
@Api(value="API Brewery")
public class BreweryController {

    @Autowired
    private BreweryBusiness breweryBusiness;
    
    @GetMapping("/cidades")
    @ApiOperation("Retorna a busca por cidades")
    public  List<BrewerieWrapper> buscaCidades(@RequestParam Optional<String> cidade) {
        return breweryBusiness.findCities(cidade);
    }

    @GetMapping("/cidade/{id}")
    @ApiOperation("Faz pesquisa por cervejaria por Id")
    public BrewerieWrapper buscaPorId(@RequestParam("id") String id){
        return breweryBusiness.findId(id);
    }

    @PostMapping("/cidade/{email}")
    @ApiOperation("Avalia a cervejaria")
    public String validarEmail(@RequestParam("email") String email, @RequestBody Double stars){
        return breweryBusiness.addRating(email, stars);
    }
}
