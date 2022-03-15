package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.domain.Brewerie;
import com.ciandt.bootcamp.Java.domain.Rating;
import com.ciandt.bootcamp.Java.domain.RatingRepository;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @Author Augusto Santos
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class BreweryBusiness {

    private final BrewerieFinderService finderService;
    private final RatingRepository ratingRepository;

    public List<BrewerieWrapper> findCities(Optional<String> cidade) {
        final List<Brewerie> breweries = cidade.isEmpty() ? finderService.findAll() : finderService.findByCity(cidade.get());
        // Converter Brewerie para BrewerieWrapper e retornar
        return null;
    }

    public BrewerieWrapper findId(String id) {
        final Brewerie brewerie = finderService.findById(id);
        // Converter Brewerie para BrewerieWrapper e retornar
        return null;
    }

    public void addRating(String email, Double stars) {
        //Validar email durante a adição
    }
}
