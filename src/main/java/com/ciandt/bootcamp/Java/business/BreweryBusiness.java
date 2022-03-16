package com.ciandt.bootcamp.Java.business;

import com.ciandt.bootcamp.Java.business.util.OrderBreweries;
import com.ciandt.bootcamp.Java.domain.Brewerie;
import com.ciandt.bootcamp.Java.domain.RatingRepository;
import com.ciandt.bootcamp.Java.domain.wrapper.BrewerieWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private final OrderBreweries orderBreweries;

    public List<BrewerieWrapper> findCities(Optional<String> cidade) {
        final List<Brewerie> breweries = cidade.isEmpty() ? finderService.findAll() : finderService.findByCity(cidade.get());

        List<BrewerieWrapper> brewerieWrapperList = new ArrayList<>();

        for (int i = 0; i < breweries.size(); i++) {

            BrewerieWrapper brewerieWrapper = new BrewerieWrapper(breweries
                    .get(i),
                    ratingRepository
                            .findByBrewerieId(breweries
                                    .get(i)
                                    .getId()));

            brewerieWrapperList.add(brewerieWrapper);

        }

        orderBreweries.orderBreweries(brewerieWrapperList);

        return brewerieWrapperList;
    }

    public BrewerieWrapper findId(String id) {
        final Brewerie brewerie = finderService.findById(id);
        return new BrewerieWrapper(brewerie, ratingRepository.findByBrewerieId(id));
    }

    public void addRating(String email, Double stars, String brewerieId) {
        //Validar email durante a adição
    }
}
