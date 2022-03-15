package com.ciandt.bootcamp.Java.application;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/Brewery")
@Api(value="API Brewery")
public class BreweryController {

    @Autowired
    BreweryBusiness breweryBusiness;
    
    @GetMapping("/cidades")
    @ApiOperation("Retorna a busca por cidades")
    public  List<Cervejaria> buscaCidades(@RequestParam Optional<String> cidade){
        return breweryBusiness.findCities(cidade);
    }

    @GetMapping("/cidade/{id}")
    @ApiOperation("Faz pesquisa por cervejaria por Id")
    public Cervejaria buscaPorId(@RequestParam("id") int id){
        return breweryBusiness.findId(id);
    }

    @PostMapping("/cidade/{email}")
    @ApiOperation("Avalia a cervejaria")
    public String validarEmail(@RequestParam("email") String email, @RequestBody int star){
        return breweryBusiness.findEmail(email);
    }
}
